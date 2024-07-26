package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;

public final class Resource {
    private final PathTemplate pathTemplate;
    private final Map<HttpVerb, Handler> map;

    public Resource(PathTemplate pathTemplate) {
        this.pathTemplate = pathTemplate;
        this.map = new HashMap<>();
    }

    public boolean matches(String path) {
        return path().matches(path);
    }

    public void put(HttpVerb verb, Handler handler) {
        map.put(verb, handler);
    }

    public Handler get(HttpVerb verb) {
        return map.get(verb);
    }

    public PathTemplate path() {
        return pathTemplate;
    }
}
