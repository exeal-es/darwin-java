package com.exeal.darwin;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Resources {
    private final List<Resource> resources;

    public Resources() {
        this.resources = new LinkedList<>();
    }

    public void add(HttpVerb verb, PathTemplate pathTemplate, Handler handler) {
        var resource = findResource(pathTemplate);
        if (resource == null) {
            resource = new Resource(pathTemplate);
            resources.add(resource);
        }
        resource.put(verb, handler);
    }

    public HttpResponse findAndApply(HttpRequest request) {
        var resource = findResource(request.path());
        if (resource == null) {
            return HttpResponse.notFound("Not Found");
        }
        var handler = resource.get(request.verb());
        if (handler == null) {
            return HttpResponse.methodNotAllowed();
        }

        try {
            return handler.apply(request);
        } catch (Exception e) {
            return HttpResponse.internalServerError(e.getMessage());
        }
    }

    private Resource findResource(PathTemplate pathTemplate) {
        for (var resource : resources) {
            if (resource.path().equals(pathTemplate)) {
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
}
