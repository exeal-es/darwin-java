package com.exeal.darwin;

public record Request(QueryParams queryParams, String path, PathTemplate pathTemplate) {
    public String queryParam(String name) {
        return this.queryParams.get(name);
    }

    public String pathParam(String name) {
        return pathTemplate.extractParam(path, name);
    }
}
