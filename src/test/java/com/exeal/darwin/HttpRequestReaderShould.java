package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HttpRequestReaderShould {
    @Test
    void testValidHttpRequestWithQueryParams() throws IOException {
        String httpRequest = "GET /path?name=value HTTP/1.1";
        BufferedReader in = new BufferedReader(new StringReader(httpRequest));
        HttpRequest request = HttpRequestReader.readHttpRequest(in);

        assertEquals(HttpVerb.GET, request.verb());
        assertEquals("/path", request.path());
        assertEquals("value", request.queryParams().get("name"));
    }

    @Test
    void testValidHttpRequestWithoutQueryParams() throws IOException {
        String httpRequest = "GET /path HTTP/1.1";
        BufferedReader in = new BufferedReader(new StringReader(httpRequest));
        HttpRequest request = HttpRequestReader.readHttpRequest(in);

        assertEquals(HttpVerb.GET, request.verb());
        assertEquals("/path", request.path());
        assertTrue(request.queryParams().isEmpty());
    }

    @Test
    void testInvalidHttpRequestNullLine() {
        BufferedReader in = new BufferedReader(new StringReader(""));
        Exception exception = assertThrows(MalformedHttpRequestException.class, () -> {
            HttpRequestReader.readHttpRequest(in);
        });

        assertEquals("Invalid HTTP request: null line", exception.getMessage());
    }

    @Test
    void testInvalidHttpRequestMalformedLine() {
        String httpRequest = "GET";
        BufferedReader in = new BufferedReader(new StringReader(httpRequest));
        Exception exception = assertThrows(MalformedHttpRequestException.class, () -> {
            HttpRequestReader.readHttpRequest(in);
        });

        assertEquals("Invalid HTTP request: GET", exception.getMessage());
    }

    @Test
    void testIncludeHeaders() throws IOException {
        String httpRequest = "GET /path HTTP/1.1\n" +
                "Host: localhost\n" +
                "User-Agent: curl/7.68.0\n" +
                "Accept: */*\n" +
                "Connection: keep-alive\n" +
                "\n";
        BufferedReader in = new BufferedReader(new StringReader(httpRequest));
        HttpRequest request = HttpRequestReader.readHttpRequest(in);

        assertEquals("localhost", request.headers().get("Host"));
        assertEquals("curl/7.68.0", request.headers().get("User-Agent"));
        assertEquals("*/*", request.headers().get("Accept"));
        assertEquals("keep-alive", request.headers().get("Connection"));
    }
}
