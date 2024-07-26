package com.exeal.darwin;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequestReader {
    static HttpRequest readHttpRequest(BufferedReader in) throws IOException {
        String line = in.readLine();
        if (line == null) {
            throw new MalformedHttpRequestException("Invalid HTTP request: null line");
        }
        String[] requestParts = line.split(" ");
        if (requestParts.length <= 1) {
            throw new MalformedHttpRequestException("Invalid HTTP request: " + line);
        }
        String verb = requestParts[0];
        String fullPath = requestParts[1];
        String[] pathParts = fullPath.split("\\?");
        QueryParams queryParams = pathParts.length > 1 ? new QueryString(pathParts[1]).readQueryParams() : QueryParams.empty();

        String path = pathParts[0];
        return new HttpRequest(parseHttpVerb(verb), path, queryParams);
    }

    private static HttpVerb parseHttpVerb(String verb) {
        if (verb.equals("GET")) return HttpVerb.GET;
        else return HttpVerb.POST;
    }
}
