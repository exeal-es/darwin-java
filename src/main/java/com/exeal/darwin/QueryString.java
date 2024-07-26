package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;

public final class QueryString {
    private final String queryString;

    public QueryString(String queryString) {
        this.queryString = queryString;
    }

    public ParameterBag readQueryParams() {
        Map<String, String> queryParams = new HashMap<>();
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            if (kv.length > 1) {
                queryParams.put(kv[0], kv[1]);
            }
        }
        return new ParameterBag(queryParams);
    }
}
