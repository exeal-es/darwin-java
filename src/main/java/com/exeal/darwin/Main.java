package com.exeal.darwin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

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
        HttpRequest httpRequest = HttpRequestReader.readHttpRequest(in);
        HttpResponse httpResponse = handleResponse(httpRequest);

        OutputStream out = clientSocket.getOutputStream();
        out.write(httpResponse.payload().getBytes("UTF-8"));
        out.flush();
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
