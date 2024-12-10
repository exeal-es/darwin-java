package com.exeal.darwin;

public class CreatedResponse extends HttpResponse {
    public CreatedResponse(String body) {
        super(201, body);
    }

    protected String statusCodeString() {
        return "Created";
    }
}
