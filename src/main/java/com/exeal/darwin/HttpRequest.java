package com.exeal.darwin;

public final class HttpRequest {
    private final HttpVerb verb;
    private final String path;
    private final ParameterBag queryParams;

    public HttpRequest(HttpVerb verb, String path) {
        this(verb, path, ParameterBag.empty());
    }

    public HttpRequest(HttpVerb verb, String path, ParameterBag queryParams) {
        this.verb = verb;
        this.path = path;
        this.queryParams = queryParams;
    }

    public HttpVerb verb() {
        return verb;
    }

    public String path() {
        return path;
    }

    public ParameterBag queryParams() {
        return queryParams;
    }
}