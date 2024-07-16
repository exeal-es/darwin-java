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
        OutputStream out = clientSocket.getOutputStream();

        String line = in.readLine();
        if (line != null) {
            String[] requestParts = line.split(" ");
            if (requestParts.length > 1) {
                String path = requestParts[1];
                String response = handleResponse(path);

                out.write(response.getBytes("UTF-8"));
                out.flush();
            }
        }
    }

    private static String handleResponse(String path) {
        String response;

        if (path.equals("/hello")) {
            response = "HTTP/1.1 200 OK\r\n\r\nWelcome!";
        } else {
            response = "HTTP/1.1 404 Not Found\r\n\r\nNot Found";
        }
        return response;
    }
}
