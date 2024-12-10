package com.exeal.darwin;

public abstract class HttpResponse {
    protected final int statusCode;
    protected final String body;
    private final String contentType;

    protected HttpResponse(int statusCode, String body) {
        this(statusCode, body, "text/plain");
    }

    protected HttpResponse(int statusCode, String body, String contentType) {
        this.statusCode = statusCode;
        this.body = body;
        this.contentType = contentType;
    }

    public static HttpResponse ok(String body) {
        return new SuccessfulResponse(200, body);
    }

    public static HttpResponse created(String body) {
        return new SuccessfulResponse(201, body);
    }

    public static HttpResponse notFound() {
        return new ErrorResponse(404, "Not Found", "application/problem+json");
    }

    public static HttpResponse methodNotAllowed() {
        return new ErrorResponse(405, "", "application/problem+json");
    }

    public static HttpResponse internalServerError(String body) {
        return new SuccessfulResponse(500, body);
    }

    public static HttpResponse forbidden() {
        return new ErrorResponse(403, "Access not allowed", "application/problem+json");
    }

    public String payload() {
        return "HTTP/1.1 " + statusCode + " " + statusCodeString() + "\r\n" + contentTypeString() + "\r\n\r\n" + body();
    }

    private String contentTypeString() {
        return "Content-Type: " + contentType;
    }

    protected String statusCodeString() {
        return switch (statusCode) {
            case 200 -> "OK";
            case 201 -> "Created";
            case 403 -> "Forbidden";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            case 500 -> "Internal Server Error";
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
