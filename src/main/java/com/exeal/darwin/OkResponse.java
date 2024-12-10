package com.exeal.darwin;

public class OkResponse extends HttpResponse {
    public OkResponse(String body) {
        super(200, body);
    }

    protected String statusCodeString() {
        return "OK";
    }
}
