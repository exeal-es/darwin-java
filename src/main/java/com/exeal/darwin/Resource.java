package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class Resource {
    private final Path path;
    private final Map<HttpVerb, Function<HttpRequest, HttpResponse>> map;

    public Resource(Path path) {
        this.path = path;
        this.map = new HashMap<>();
    }

    public boolean matches(String path) {
        return path().matches(path);
    }

    public void put(HttpVerb verb, Function<HttpRequest, HttpResponse> callback) {
        map.put(verb, callback);
    }

    public Function<HttpRequest, HttpResponse> get(HttpVerb verb) {
        return map.get(verb);
    }

    public Path path() {
        return path;
    }

    public Map<HttpVerb, Function<HttpRequest, HttpResponse>> map() {
        return map;
    }
}
