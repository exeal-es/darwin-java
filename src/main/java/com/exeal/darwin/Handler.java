package com.exeal.darwin;

import java.util.function.Function;

public record Handler(Function<HttpRequest, HttpResponse> callback) {
    public HttpResponse apply(HttpRequest request) {
        return callback.apply(request);
    }
}
