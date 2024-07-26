package com.exeal.darwin;

import java.util.function.Function;

public record Handler(Function<Request, HttpResponse> callback) {
    public HttpResponse apply(Request request) {
        return callback.apply(request);
    }
}
