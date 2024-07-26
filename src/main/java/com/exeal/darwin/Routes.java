package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Routes {
    private Map<String, Function<HttpRequest, HttpResponse>> routes;

    public Routes() {
        this.routes = new HashMap<>();
    }

    public boolean contains(String path) {
        return routes.containsKey(path);
    }

    public HttpResponse findAndApply(HttpRequest request) {
        Function<HttpRequest, HttpResponse> callback = routes.get(request.path());
        if (callback == null) {
            return HttpResponse.notFound("Not Found");
        }
        return callback.apply(request);
    }

    public void add(String verb, String path, Function<HttpRequest, HttpResponse> callback) {
        routes.put(path, callback);
    }
}
