package com.exeal.darwin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Thread serverThread = new Thread(Main::listenHttpAndRespond);
        serverThread.start();
    }

    private static void listenHttpAndRespond() {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is listening on port 8080");

            while(true){
                try (Socket clientSocket = serverSocket.accept()) {
                    handleClientMessage(clientSocket);
                } catch (IOException e) {
                    System.err.println("Error handling client: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }

    private static void handleClientMessage(Socket clientSocket) throws IOException {
        System.out.println("New client connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        HttpRequest httpRequest = readHttpRequest(in);
        HttpResponse response = handleResponse(httpRequest);

        OutputStream out = clientSocket.getOutputStream();
        response.writeTo(out);
    }

    private static HttpRequest readHttpRequest(BufferedReader in) throws IOException {
        String line = in.readLine();
        if (line == null) {
            throw new MalformedHttpRequestException("Invalid HTTP request: null line");
        }
        String[] requestParts = line.split(" ");
        if (requestParts.length <= 1) {
            throw new MalformedHttpRequestException("Invalid HTTP request: " + line);
        }
        String verb = requestParts[0];
        String fullPath = requestParts[1];
        String[] pathParts = fullPath.split("\\?");
        String path = pathParts[0];

        Map<String, String> queryParams = readQueryParams(pathParts);

        return new HttpRequest(verb, path, queryParams);
    }

    private static Map<String, String> readQueryParams(String[] pathParts) {
        Map<String, String> queryParams = new HashMap<>();
        if (pathParts.length > 1) {
            String[] pairs = pathParts[1].split("&");
            for (String pair : pairs) {
                String[] kv = pair.split("=");
                if (kv.length > 1) {
                    queryParams.put(kv[0], kv[1]);
                }
            }
        }
        return queryParams;
    }

    private static HttpResponse handleResponse(HttpRequest httpRequest) {
        String payload;

        if (httpRequest.path().equals("/greet")) {
            String name = httpRequest.queryParam("name");
            String body = "Hello " + name + "!";
            payload = "HTTP/1.1 200 OK\r\n\r\n" + body;
        } else if (httpRequest.verb().equals("POST")) {
            payload = "HTTP/1.1 201 Created\r\n\r\nCreated!";
        } else if (httpRequest.path().equals("/hello")) {
            payload = "HTTP/1.1 200 OK\r\n\r\nWelcome!";
        } else {
            payload = "HTTP/1.1 404 Not Found\r\n\r\nNot Found";
        }
        return new HttpResponse(payload);
    }
}
