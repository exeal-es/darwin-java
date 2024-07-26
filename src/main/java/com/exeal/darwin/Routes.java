package com.exeal.darwin;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Routes {
    private final List<Resource> resources;

    public Routes() {
        this.resources = new LinkedList<>();
    }

    public HttpResponse findAndApply(HttpRequest request) {
        var map = findResource(request.path());
        if (map == null) {
            return HttpResponse.notFound("Not Found");
        }
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

    private Resource findResource(Path path) {
        for (var resource : resources) {
            if (resource.path().equals(path)) {
                return resource;
            }
        }
        return null;
    }

    private Resource findResource(String path) {
        for (var resource : resources) {
            if (resource.matches(path)) {
                return resource;
            }
        }
        return null;
    }

    public void add(HttpVerb verb, Path path, Function<HttpRequest, HttpResponse> callback) {
        var resource = findResource(path);
        if (resource == null) {
            resource = new Resource(path);
            resources.add(resource);
        }
        resource.put(verb, callback);
    }
}
