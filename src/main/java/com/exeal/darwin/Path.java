package com.exeal.darwin;

public record Path(String value) {
    public boolean matches(String path) {
        String regex = value.replaceAll("\\{\\w+\\}", "\\\\w+");
        return path.matches(regex);
    }
}
