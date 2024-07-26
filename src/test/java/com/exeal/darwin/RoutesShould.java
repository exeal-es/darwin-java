package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoutesShould {
     @Test
     public void returnCallbackWhenPathIsContained() {
         Routes routes = new Routes();
         Function<HttpRequest, HttpResponse> callback = (request) -> HttpResponse.ok("OK");
         routes.add(HttpVerb.GET, new Path("/test"), callback);

         HttpRequest request = new HttpRequest(HttpVerb.GET, new Path("/test"));
         HttpResponse response = routes.findAndApply(request);

         assertEquals(200, response.statusCode());
     }

     @Test
     public void returnNotFoundWhenPathIsNotContained() {
         Routes routes = new Routes();

         HttpRequest request = new HttpRequest(HttpVerb.GET, new Path("/test"));
         HttpResponse response = routes.findAndApply(request);

         assertEquals(404, response.statusCode());
     }

    @Test
    public void returnMethodNotAllowedWhenPathIsContainedButForOtherMethod() {
        Routes routes = new Routes();
        routes.add(HttpVerb.GET, new Path("/test"), (req) -> HttpResponse.ok("OK"));

        HttpResponse response = routes.findAndApply(new HttpRequest(HttpVerb.POST, new Path("/test")));

        assertEquals(405, response.statusCode());
    }

    @Test
    public void returnMethodNotAllowedWhenPathIsContainedButForOtherMethod2() {
        Routes routes = new Routes();
        routes.add(HttpVerb.POST, new Path("/test"), (req) -> HttpResponse.ok("OK"));

        HttpResponse response = routes.findAndApply(new HttpRequest(HttpVerb.GET, new Path("/test")));

        assertEquals(405, response.statusCode());
    }

     @Test
     public void addMultipleRoutes() {
         Routes routes = new Routes();
         Function<HttpRequest, HttpResponse> callback1 = (request) -> HttpResponse.ok("OK");
         Function<HttpRequest, HttpResponse> callback2 = (request) -> HttpResponse.created("Created");
         routes.add(HttpVerb.GET, new Path("/test"), callback1);
         routes.add(HttpVerb.GET, new Path("/test2"), callback2);
         HttpRequest request1 = new HttpRequest(HttpVerb.GET, new Path("/test"));
         HttpResponse response1 = routes.findAndApply(request1);
         assertEquals(200, response1.statusCode());
         HttpRequest request2 = new HttpRequest(HttpVerb.GET, new Path("/test2"));
         HttpResponse response2 = routes.findAndApply(request2);
         assertEquals(201, response2.statusCode());
     }
}
