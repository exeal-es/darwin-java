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

    private String problemDetailString() {
        return switch (statusCode) {
            case 403 -> "Access not allowed";
            case 404 -> "Resource not found";
            case 405 -> "Method not allowed";
            default -> "";
        };
    }

    public int statusCode() {
        return statusCode;
    }

    public String body() {
        if (isError()) {
            return getProblemDetailsTemplate(statusCodeString(), problemDetailString());
        }
        return body;
    }

    private boolean isError() {
        return statusCode >= 400;
    }

    private static String getProblemDetailsTemplate(String statusCodeString, String detail) {
        return "{\"title\":\"" + statusCodeString + "\",\"detail\":\"" + detail + "\"}";
    }
}
