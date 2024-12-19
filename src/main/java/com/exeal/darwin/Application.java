package com.exeal.darwin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Function;

public class Application {

    private final Resources resources;

    public Application() {
        resources = new Resources();
    }

    public void get(String path, boolean isSecured, Function<Request, HttpResponse> callback) {
        resources.add(HttpVerb.GET, new PathTemplate(path), new Handler(callback));
    }

    public void get(String path, Function<Request, HttpResponse> callback) {
        get(path, false, callback);
    }

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
        return resources.findAndApply(httpRequest);
    }
}
