package com.exeal.darwin;

import com.exeal.darwin.httpresponse.ForbiddenResponse;
import com.exeal.darwin.httpresponse.InternalServerErrorResponse;
import com.exeal.darwin.httpresponse.MethodNotAllowedResponse;
import com.exeal.darwin.httpresponse.NotFoundResponse;

public class HttpResponseFactory {
    public static HttpResponse ok(String body) {
        return new SuccessfulResponse(200, body);
    }

    public static HttpResponse created(String body) {
        return new SuccessfulResponse(201, body);
    }

    public static HttpResponse notFound() {
        return new NotFoundResponse();
    }

    public static HttpResponse methodNotAllowed() {
        return new MethodNotAllowedResponse();
    }

    public static HttpResponse internalServerError(String body) {
        return new InternalServerErrorResponse(body);
    }

    public static HttpResponse forbidden() {
        return new ForbiddenResponse();
    }
}
