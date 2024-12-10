package com.exeal.darwin.httpresponse;

public class NotFoundResponse extends ErrorResponse {
    public NotFoundResponse() {
        super(404, "Not Found");
    }

    protected String statusCodeString() {
        return "Not Found";
    }
}
