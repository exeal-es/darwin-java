package com.exeal.darwin;

public record Request(QueryParams queryParams) {
    public String queryParam(String name) {
        return this.queryParams.get(name);
    }

    public String pathParam(String name) {
        throw new RuntimeException("Not implemented");
    }
}
