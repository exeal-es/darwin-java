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

    public Function<HttpRequest, HttpResponse> find(HttpRequest request) {
        return routes.get(request.path());
    }

    public void add(String verb, String path, Function<HttpRequest, HttpResponse> callback) {
        routes.put(path, callback);
    }
}
