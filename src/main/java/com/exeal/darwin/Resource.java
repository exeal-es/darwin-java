package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;

public final class Resource {
    private final PathTemplate pathTemplate;
    private final Map<HttpVerb, Handler> handlersByVerb;

    public Resource(PathTemplate pathTemplate) {
        this.pathTemplate = pathTemplate;
        this.handlersByVerb = new HashMap<>();
    }

    public HttpResponse accept(HttpRequest request) {
        var handler = handlersByVerb.get(request.verb());
        if (handler == null) {
            return HttpResponse.methodNotAllowed();
        }

        try {
            return handler.apply(new Request(request.queryParams()));
        } catch (Exception e) {
            return HttpResponse.internalServerError(e.getMessage());
        }
    }

    public boolean hasPathTemplate(PathTemplate pathTemplate) {
        return this.pathTemplate.equals(pathTemplate);
    }

    public boolean matches(String path) {
        return pathTemplate.matches(path);
    }

    public void put(HttpVerb verb, Handler handler) {
        handlersByVerb.put(verb, handler);
    }
}
