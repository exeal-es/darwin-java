package com.exeal.darwin.httpresponse;

public class ForbiddenResponse extends ErrorResponse {
    public ForbiddenResponse() {
        super(403, "Access not allowed");
    }

    protected String statusCodeString() {
        return "Forbidden";
    }
}
