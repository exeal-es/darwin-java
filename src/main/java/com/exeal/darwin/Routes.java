package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Routes {
    private Map<String, Map<HttpVerb, Function<HttpRequest, HttpResponse>>> routes;

    public Routes() {
        this.routes = new HashMap<>();
    }

    public boolean contains(String path) {
        return routes.containsKey(path);
    }

    public HttpResponse findAndApply(HttpRequest request) {
        Map<HttpVerb, Function<HttpRequest, HttpResponse>> map = routes.get(request.path());
        if (map == null) {
            return HttpResponse.notFound("Not Found");
        } else {
            Function<HttpRequest, HttpResponse> callback = map.get(request.verb());
            if (callback == null) {
                return HttpResponse.methodNotAllowed();
            }
            return callback.apply(request);
        }
    }

    public void add(HttpVerb verb, String path, Function<HttpRequest, HttpResponse> callback) {
        Map<HttpVerb, Function<HttpRequest, HttpResponse>> map = routes.getOrDefault(path, new HashMap<>());
        map.put(verb, callback);
        routes.put(path, map);
    }
}
