package com.exeal.darwin.httpresponse;

import com.exeal.darwin.ErrorResponse;

public class NotFoundResponse extends ErrorResponse {
    public NotFoundResponse() {
        super(404, "Not Found");
    }
}
