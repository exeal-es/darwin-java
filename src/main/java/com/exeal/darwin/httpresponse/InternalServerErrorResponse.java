package com.exeal.darwin.httpresponse;

import com.exeal.darwin.ErrorResponse;

public class InternalServerErrorResponse extends ErrorResponse {
    public InternalServerErrorResponse(String body) {
        super(500, body);
    }
}
