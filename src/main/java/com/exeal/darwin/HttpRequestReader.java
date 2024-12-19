package com.exeal.darwin;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        ParameterBag queryParams = pathParts.length > 1 ? new QueryString(pathParts[1]).readQueryParams() : ParameterBag.empty();

        String path = pathParts[0];

        ParameterBag headers = readHeaders(in);

        return new HttpRequest(HttpVerb.parse(verb), path, queryParams, headers);
    }

    private static ParameterBag readHeaders(BufferedReader in) throws IOException {
        Map<String, String> headersMap = new HashMap<>();
        String line;
        while ((line = in.readLine()) != null && !line.isEmpty()) {
            String[] headerParts = line.split(": ", 2);
            if (headerParts.length == 2) {
                headersMap.put(headerParts[0], headerParts[1]);
            }
        }
        return new ParameterBag(headersMap);
    }
}
