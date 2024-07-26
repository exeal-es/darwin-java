package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HttpResponseTest {

    @Test
    public void testPayload() {
        HttpResponse response = new HttpResponse("HTTP/1.1 200 OK\r\n\r\nHello World!");
        assertEquals("HTTP/1.1 200 OK\r\n\r\nHello World!", response.payload());
    }
}
