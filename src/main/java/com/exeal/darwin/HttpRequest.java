package com.exeal.darwin;

public final class HttpRequest {
    private final String path;

    public HttpRequest(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}