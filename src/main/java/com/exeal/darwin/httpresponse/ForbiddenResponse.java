package com.exeal.darwin.httpresponse;

import com.exeal.darwin.ErrorResponse;

public class ForbiddenResponse extends ErrorResponse {
    public ForbiddenResponse() {
        super(403, "Access not allowed");
    }

    protected String statusCodeString() {
        return "Forbidden";
    }
}
