package com.exeal.darwin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Application {

    public void run(int port) {
        Thread serverThread = new Thread(() -> listenHttpAndRespond(port));
        serverThread.start();
    }

    private void listenHttpAndRespond(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

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

    private void handleClientMessage(Socket clientSocket) throws IOException {
        System.out.println("New client connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        HttpRequest httpRequest = HttpRequestReader.readHttpRequest(in);
        HttpResponse httpResponse = handleResponse(httpRequest);

        OutputStream out = clientSocket.getOutputStream();
        out.write(httpResponse.payload().getBytes("UTF-8"));
        out.flush();
    }

    private HttpResponse handleResponse(HttpRequest httpRequest) {
        if (routes.containsKey(httpRequest.path())) {
            if (httpRequest.verb().equals("GET")) {
                Function<HttpRequest, HttpResponse> callback = routes.get(httpRequest.path());
                HttpResponse response = callback.apply(httpRequest);
                return response;
            } else {
                return HttpResponse.methodNotAllowed();
            }
        } else if (httpRequest.verb().equals("POST")) {
            return HttpResponse.created("Created!");
        } else {
            return HttpResponse.notFound("Not Found");
        }
    }

    private final Map<String, Function<HttpRequest, HttpResponse>> routes = new HashMap<>();

    public void get(String path, Function<HttpRequest, HttpResponse> callback) {
        routes.put(path, callback);
    }
}
