package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HttpResponseShould {

    @Test
    public void create_ok() {
        HttpResponse response = HttpResponse.ok("Hello World!");
        assertEquals("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello World!", response.payload());
    }

    @Test
    public void create_created() {
        HttpResponse response = HttpResponse.created("Hello World!");
        assertEquals("HTTP/1.1 201 Created\r\n" +
                "Content-Type: text/plain\r\n\r\nHello World!", response.payload());
    }

    @Test
    public void create_not_found() {
        HttpResponse response = HttpResponse.notFound();
        assertEquals("HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: application/problem+json\r\n\r\n{\"title\":\"Not Found\",\"detail\":\"Resource not found\"}", response.payload());
    }

    @Test
    public void create_forbidden() {
        HttpResponse response = HttpResponse.forbidden();
        assertEquals("HTTP/1.1 403 Forbidden\r\n" +
                "Content-Type: application/problem+json\r\n\r\n{\"title\":\"Forbidden\",\"detail\":\"Access not allowed\"}", response.payload());
    }

    @Test
    public void create_method_not_allowed() {
        HttpResponse response = HttpResponse.methodNotAllowed();
        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n" +
                "Content-Type: application/problem+json\r\n\r\n{\"title\":\"Method Not Allowed\",\"detail\":\"Method not allowed\"}", response.payload());
    }

    @Test
    public void create_internal_server_error() {
        HttpResponse response = HttpResponse.internalServerError("La has liado pollito!");
        assertEquals("HTTP/1.1 500 Internal Server Error\r\n" +
                "Content-Type: application/problem+json\r\n\r\n{\"title\":\"Internal Server Error\",\"detail\":\"La has liado pollito!\"}", response.payload());
    }
}
