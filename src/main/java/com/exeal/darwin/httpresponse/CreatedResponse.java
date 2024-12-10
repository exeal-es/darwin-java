package com.exeal.darwin.httpresponse;

import com.exeal.darwin.HttpResponse;

public class CreatedResponse extends HttpResponse {
    public CreatedResponse(String body) {
        super(201, body);
    }

    protected String statusCodeString() {
        return "Created";
    }
}
