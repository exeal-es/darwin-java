package com.exeal.darwin;

import java.util.LinkedList;
import java.util.List;

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
            return HttpResponse.notFound();
        }
        return resource.accept(request);
    }

    private Resource findResource(PathTemplate pathTemplate) {
        return resources.stream()
                .filter(resource -> resource.hasPathTemplate(pathTemplate))
                .findFirst()
                .orElse(null);
    }

    private Resource findResource(String path) {
        return resources.stream()
                .filter(resource -> resource.matches(path))
                .findFirst()
                .orElse(null);
    }
}
