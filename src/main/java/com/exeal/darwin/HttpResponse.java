package com.exeal.darwin;

public class HttpResponse {
    private final int statusCode;
    private final String body;

    private HttpResponse(int statusCode, String body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public static HttpResponse ok(String body) {
        return new HttpResponse(200, body);
    }

    public static HttpResponse created(String body) {
        return new HttpResponse(201, body);
    }

    public static HttpResponse notFound(String body) {
        return new HttpResponse(404, body);
    }

    public static HttpResponse methodNotAllowed() {
        return new HttpResponse(405, "");
    }

    public static HttpResponse internalServerError(String body) {
        return new HttpResponse(500, body);
    }

    public String payload() {
        return "HTTP/1.1 " + statusCode + " " + statusCodeString() + "\r\n\r\n" + body;
    }

    private String statusCodeString() {
        return switch (statusCode) {
            case 200 -> "OK";
            case 201 -> "Created";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            case 500 -> "Internal Server Error";
            default -> "";
        };
    }

    public int statusCode() {
        return statusCode;
    }
}
