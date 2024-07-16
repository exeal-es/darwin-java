package com.exeal.darwin;

import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private final String payload;

    public HttpResponse(String payload) {
        this.payload = payload;
    }

    public void writeTo(OutputStream out) throws IOException {
        out.write(payload.getBytes("UTF-8"));
        out.flush();
    }
}
