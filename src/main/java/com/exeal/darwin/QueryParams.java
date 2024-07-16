package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;

public class QueryParams {
    private final Map<String, String> paramsByName;

    public QueryParams(Map<String, String> paramsByName) {
        this.paramsByName = paramsByName;
    }

    static QueryParams empty() {
        Map<String, String> queryParams = new HashMap<>();
        return new QueryParams(queryParams);
    }

    public String get(String name) {
        return paramsByName.get(name);
    }
}
