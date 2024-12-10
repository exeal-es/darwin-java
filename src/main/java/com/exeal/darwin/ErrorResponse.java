package com.exeal.darwin;

public class ErrorResponse extends HttpResponse {
    public ErrorResponse(int statusCode, String title, String contentType) {
        super(statusCode, title, contentType);
    }
}
