package com.exeal.darwin;

public abstract class ErrorResponse extends HttpResponse {
    public ErrorResponse(int statusCode, String title) {
        super(statusCode, title);
    }

    protected String contentType() {
        return "application/problem+json";
    }

    public String body() {
        return getProblemDetailsTemplate(statusCodeString(), problemDetailString());
    }

    private String problemDetailString() {
        return switch (statusCode) {
            case 403 -> "Access not allowed";
            case 404 -> "Resource not found";
            case 405 -> "Method not allowed";
            case 500 -> body;
            default -> "";
        };
    }

    private static String getProblemDetailsTemplate(String statusCodeString, String detail) {
        return "{\"title\":\"" + statusCodeString + "\",\"detail\":\"" + detail + "\"}";
    }
}
