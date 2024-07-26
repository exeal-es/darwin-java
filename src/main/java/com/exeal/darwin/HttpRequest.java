package com.exeal.darwin;

public final class HttpRequest {
    private final HttpVerb verb;
    private final String path;
    private final QueryParams queryParams;

    public HttpRequest(HttpVerb verb, String path) {
        this(verb, path, QueryParams.empty());
    }

    public HttpRequest(HttpVerb verb, String path, QueryParams queryParams) {
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

    public String queryParam(String name) {
        return queryParams.get(name);
    }
}