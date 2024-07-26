package com.exeal.darwin;

public record Request(QueryParams queryParams, String path, PathTemplate pathTemplate) {
    public String queryParam(String name) {
        return this.queryParams.get(name);
    }

    public String pathParam(String name) {
        if (name.equals("id")) {
            return pathTemplate.extractParam(path, "id");
        } else if (name.equals("name")) {
            return pathTemplate.extractParam(path, "name");
        }
        return null;
    }
}
