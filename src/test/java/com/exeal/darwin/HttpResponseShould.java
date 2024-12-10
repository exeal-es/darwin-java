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
        HttpResponse response = HttpResponse.notFound("Hello World!");
        assertEquals("HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: application/problem+json\r\n\r\n{\"title\":\"Not Found\",\"detail\":\"Resource not found\"}", response.payload());
    }
}
