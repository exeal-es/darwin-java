package com.exeal.darwin;

public class ErrorResponse extends HttpResponse {
    public ErrorResponse(int statusCode, String title, String contentType) {
        super(statusCode, title, contentType);
    }

    public String body() {
        return getProblemDetailsTemplate(statusCodeString(), problemDetailString());
    }

    private String problemDetailString() {
        return switch (statusCode) {
            case 403 -> "Access not allowed";
            case 404 -> "Resource not found";
            case 405 -> "Method not allowed";
            default -> "";
        };
    }

    private static String getProblemDetailsTemplate(String statusCodeString, String detail) {
        return "{\"title\":\"" + statusCodeString + "\",\"detail\":\"" + detail + "\"}";
    }
}
