package com.exeal.darwin;

public final class HttpRequest {
    private final HttpVerb verb;
    private final String path;
    private final ParameterBag queryParams;
    private final ParameterBag headers;

    public HttpRequest(HttpVerb verb, String path) {
        this(verb, path, ParameterBag.empty(), ParameterBag.empty());
    }

    public HttpRequest(HttpVerb verb, String path, ParameterBag queryParams, ParameterBag headers) {
        this.verb = verb;
        this.path = path;
        this.queryParams = queryParams;
        this.headers = headers;
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

    public ParameterBag headers() {
        return headers;
    }
}