package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Routes {
    private Map<String, Function<HttpRequest, HttpResponse>> routes;

    public Routes() {
        this.routes = new HashMap<>();
    }

    public boolean containsKey(String path) {
        return routes.containsKey(path);
    }

    public Function<HttpRequest, HttpResponse> get(String path) {
        return routes.get(path);
    }

    public void put(String verb, String path, Function<HttpRequest, HttpResponse> callback) {
        routes.put(path, callback);
    }
}
