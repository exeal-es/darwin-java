package com.exeal.darwin;

import com.exeal.darwin.httpresponse.CreatedResponse;
import com.exeal.darwin.httpresponse.ForbiddenResponse;
import com.exeal.darwin.httpresponse.InternalServerErrorResponse;
import com.exeal.darwin.httpresponse.MethodNotAllowedResponse;
import com.exeal.darwin.httpresponse.NotFoundResponse;
import com.exeal.darwin.httpresponse.OkResponse;

public class HttpResponseFactory {
    public static HttpResponse ok(String body) {
        return new OkResponse(body);
    }

    public static HttpResponse created(String body) {
        return new CreatedResponse(body);
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
