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

    public static ParameterBag of(String name, String value) {
        var paramsByName = new HashMap<String, String>();
        paramsByName.put(name, value);
        return new ParameterBag(paramsByName);
    }

    public String get(String name) {
        return paramsByName.get(name);
    }

    public boolean has(String name) {
        return paramsByName.containsKey(name);
    }

    public boolean isEmpty() {
        return paramsByName.isEmpty();
    }
}
