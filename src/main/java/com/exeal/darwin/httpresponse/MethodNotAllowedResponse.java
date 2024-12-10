package com.exeal.darwin.httpresponse;

import com.exeal.darwin.ErrorResponse;

public class MethodNotAllowedResponse extends ErrorResponse {
    public MethodNotAllowedResponse() {
        super(405, "Method Not Allowed");
    }
}
