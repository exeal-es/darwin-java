package com.exeal.darwin;

public class SuccessfulResponse extends HttpResponse {
    public SuccessfulResponse(int statusCode, String body) {
        super(statusCode, body);
    }
}
