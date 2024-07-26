package com.exeal.darwin;

public record Request(ParameterBag queryParams, ParameterBag pathParams) {
    public String queryParam(String name) {
        return queryParams.get(name);
    }

    public String pathParam(String name) {
        return pathParams.get(name);
    }
}
