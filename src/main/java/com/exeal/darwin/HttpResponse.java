package com.exeal.darwin;

public class HttpResponse {
    private final String payload;

    public HttpResponse(String payload) {
        this.payload = payload;
    }

    public String payload() {
        return "HTTP/1.1 " + payload;
    }
}
