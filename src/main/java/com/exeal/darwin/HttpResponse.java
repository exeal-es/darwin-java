package com.exeal.darwin;

public class HttpResponse {
    private final int statusCode;
    private final String body;
    private final String contentType;

    private HttpResponse(int statusCode, String body) {
        this(statusCode, body, "text/plain");
    }

    private HttpResponse(int statusCode, String body, String contentType) {
        this.statusCode = statusCode;
        this.body = body;
        this.contentType = contentType;
    }

    public static HttpResponse ok(String body) {
        return new HttpResponse(200, body);
    }

    public static HttpResponse created(String body) {
        return new HttpResponse(201, body);
    }

    public static HttpResponse notFound() {
        return new HttpResponse(404, "Not Found", "application/problem+json");
    }

    public static HttpResponse methodNotAllowed() {
        return new HttpResponse(405, "", "application/problem+json");
    }

    public static HttpResponse internalServerError(String body) {
        return new HttpResponse(500, body);
    }

    public static HttpResponse forbidden() {
        return new HttpResponse(403, "Access not allowed", "application/problem+json");
    }

    public String payload() {
        return "HTTP/1.1 " + statusCode + " " + statusCodeString() + "\r\n" + contentTypeString() + "\r\n\r\n" + body();
    }

    private String contentTypeString() {
        return "Content-Type: " + contentType;
    }

    private String statusCodeString() {
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
        if (statusCode == 403) {
            return "{\"title\":\"Forbidden\",\"detail\":\"Access not allowed\"}";
        }
        if (statusCode == 404) {
            return "{\"title\":\"Not Found\",\"detail\":\"Resource not found\"}";
        }
        if (statusCode == 405) {
            return "{\"title\":\"Method Not Allowed\",\"detail\":\"Method not allowed\"}";
        }
        return body;
    }
}
