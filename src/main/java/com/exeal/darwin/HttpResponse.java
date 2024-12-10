package com.exeal.darwin;

public abstract class HttpResponse {
    protected final int statusCode;
    protected final String body;

    protected HttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public static HttpResponse ok(String body) {
        return new SuccessfulResponse(200, body);
    }

    public static HttpResponse created(String body) {
        return new SuccessfulResponse(201, body);
    }

    public static HttpResponse notFound() {
        return new ErrorResponse(404, "Not Found");
    }

    public static HttpResponse methodNotAllowed() {
        return new ErrorResponse(405, "");
    }

    public static HttpResponse internalServerError(String body) {
        return new ErrorResponse(500, body);
    }

    public static HttpResponse forbidden() {
        return new ErrorResponse(403, "Access not allowed");
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

    protected String statusCodeString() {
        return switch (statusCode) {
            case 200 -> "OK";
            case 201 -> "Created";
            default -> "";
        };
    }

    public int statusCode() {
        return statusCode;
    }

    public String body() {
        return body;
    }
}
