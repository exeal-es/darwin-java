package com.exeal.darwin;

public enum HttpVerb {
    POST, GET;

    public static HttpVerb parse(String verb) {
        if (verb.equals("GET")) return GET;
        else return POST;
    }
}
