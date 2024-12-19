package com.exeal.darwin;

public record Request(ParameterBag queryParams, ParameterBag pathParams, ParameterBag headers) {
    public String queryParam(String name) {
        return queryParams.get(name);
    }

    public String pathParam(String name) {
        return pathParams.get(name);
    }

    public String claim(String name) {
        return headers.get(name);
    }
}
