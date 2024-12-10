package com.exeal.darwin;

public abstract class HttpResponse {
    protected final int statusCode;
    protected final String body;

    protected HttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public String payload() {
        return "HTTP/1.1 " + statusCode + " " + statusCodeString() + "\r\n" + contentTypeString() + "\r\n\r\n" + body();
    }

    private String contentTypeString() {
        return "Content-Type: " + contentType();
    }

    protected String contentType() {
        return "text/plain";
    }

    protected abstract String statusCodeString();

    public int statusCode() {
        return statusCode;
    }

    protected String body() {
        return body;
    }
}
