package com.exeal.darwin;

public class HttpResponse {
    private final String statusCodeString;
    private final String body;

    private HttpResponse(String statusCodeString, String body) {
        this.statusCodeString = statusCodeString;
        this.body = body;
    }

    public static HttpResponse ok(String body) {
        return new HttpResponse("200 OK", body);
    }

    public static HttpResponse created(String body) {
        return new HttpResponse("201 Created", body);
    }

    public static HttpResponse notFound(String body) {
        return new HttpResponse("404 Not Found", body);
    }

    public String payload() {
        return "HTTP/1.1 " + statusCodeString + "\r\n\r\n" + body;
    }
}
