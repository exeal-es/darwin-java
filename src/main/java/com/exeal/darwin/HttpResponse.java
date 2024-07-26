package com.exeal.darwin;

public class HttpResponse {
    private final String statusCodeString;
    private final String body;

    public HttpResponse(String statusCodeString, String body) {
        this.statusCodeString = statusCodeString;
        this.body = body;
    }

    public String payload() {
        return "HTTP/1.1 " + statusCodeString + "\r\n\r\n" + body;
    }
}
