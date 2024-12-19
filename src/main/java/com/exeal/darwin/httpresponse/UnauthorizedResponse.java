package com.exeal.darwin.httpresponse;

import com.exeal.darwin.HttpResponse;

public class UnauthorizedResponse extends HttpResponse {
    public UnauthorizedResponse() {
        super(401, "Unauthorized");
    }

    protected String statusCodeString() {
        return "Unauthorized";
    }
}
