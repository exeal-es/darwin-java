package com.exeal.darwin;

public final class HttpRequest {
    private final HttpVerb verb;
    private final Path path;
    private final QueryParams queryParams;

    public HttpRequest(HttpVerb verb, Path path) {
        this(verb, path, QueryParams.empty());
    }

    public HttpRequest(HttpVerb verb, Path path, QueryParams queryParams) {
        this.verb = verb;
        this.path = path;
        this.queryParams = queryParams;
    }

    public HttpVerb verb() {
        return verb;
    }

    public Path path() {
        return path;
    }

    public String queryParam(String name) {
        return queryParams.get(name);
    }
}