package com.exeal.darwin.httpresponse;

public class MethodNotAllowedResponse extends ErrorResponse {
    public MethodNotAllowedResponse() {
        super(405, "Method Not Allowed");
    }

    protected String statusCodeString() {
        return "Method Not Allowed";
    }
}
