package com.exeal.darwin;

import java.util.HashMap;
import java.util.Map;

public class ParameterBag {
    private final Map<String, String> paramsByName;

    public ParameterBag(Map<String, String> paramsByName) {
        this.paramsByName = paramsByName;
    }

    static ParameterBag empty() {
        return new ParameterBag(new HashMap<>());
    }

    public String get(String name) {
        return paramsByName.get(name);
    }
}
