package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HttpResponseShould {

    @Test
    public void create_ok() {
        HttpResponse response = HttpResponseFactory.ok("Hello World!");
        assertEquals(200, response.statusCode());
        assertEquals("HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\n\r\nHello World!", response.payload());
    }

    @Test
    public void create_created() {
        HttpResponse response = HttpResponseFactory.created("Hello World!");
        assertEquals(201, response.statusCode());
        assertEquals("HTTP/1.1 201 Created\r\n" +
                "Content-Type: text/plain\r\n\r\nHello World!", response.payload());
    }

    @Test
    public void create_not_found() {
        HttpResponse response = HttpResponseFactory.notFound();
        assertEquals(404, response.statusCode());
        assertEquals("HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: application/problem+json\r\n\r\n{\"title\":\"Not Found\",\"detail\":\"Resource not found\"}", response.payload());
    }

    @Test
    public void create_forbidden() {
        HttpResponse response = HttpResponseFactory.forbidden();
        assertEquals(403, response.statusCode());
        assertEquals("HTTP/1.1 403 Forbidden\r\n" +
                "Content-Type: application/problem+json\r\n\r\n{\"title\":\"Forbidden\",\"detail\":\"Access not allowed\"}", response.payload());
    }

    @Test
    public void create_method_not_allowed() {
        HttpResponse response = HttpResponseFactory.methodNotAllowed();
        assertEquals(405, response.statusCode());
        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n" +
                "Content-Type: application/problem+json\r\n\r\n{\"title\":\"Method Not Allowed\",\"detail\":\"Method not allowed\"}", response.payload());
    }

    @Test
    public void create_internal_server_error() {
        HttpResponse response = HttpResponseFactory.internalServerError("La has liado pollito!");
        assertEquals(500, response.statusCode());
        assertEquals("HTTP/1.1 500 Internal Server Error\r\n" +
                "Content-Type: application/problem+json\r\n\r\n{\"title\":\"Internal Server Error\",\"detail\":\"La has liado pollito!\"}", response.payload());
    }
}
