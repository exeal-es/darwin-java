package com.exeal.darwin;

import java.util.Map;

public class QueryParams {
    private final Map<String, String> paramsByName;

    public QueryParams(Map<String, String> paramsByName) {
        this.paramsByName = paramsByName;
    }

    public String get(String name) {
        return paramsByName.get(name);
    }
}
