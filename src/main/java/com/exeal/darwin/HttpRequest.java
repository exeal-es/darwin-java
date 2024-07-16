package com.exeal.darwin;

import java.util.Map;

public final class HttpRequest {
    private final String verb;
    private final String path;
    private final QueryParams queryParams;

    public HttpRequest(String verb, String path, QueryParams queryParams) {
        this.verb = verb;
        this.path = path;
        this.queryParams = queryParams;
    }

    public String verb() {
        return verb;
    }

    public String path() {
        return path;
    }

    public String queryParam(String name) {
        return queryParams.get(name);
    }
}