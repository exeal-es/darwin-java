package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Routes {
    private final Map<Path, Map<HttpVerb, Function<HttpRequest, HttpResponse>>> routes;

    public Routes() {
        this.routes = new HashMap<>();
    }

    public HttpResponse findAndApply(HttpRequest request) {
        var map = routes.get(request.path());
        if (map == null) {
            return HttpResponse.notFound("Not Found");
        } else {
            var callback = map.get(request.verb());
            if (callback == null) {
                return HttpResponse.methodNotAllowed();
            }
            try {
                return callback.apply(request);
            } catch (Exception e) {
                return HttpResponse.internalServerError(e.getMessage());
            }

        }
    }

    public void add(HttpVerb verb, Path path, Function<HttpRequest, HttpResponse> callback) {
        var map = routes.getOrDefault(path, new HashMap<>());
        map.put(verb, callback);
        routes.put(path, map);
    }
}
