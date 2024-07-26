package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoutesShould {
     @Test
     public void returnTrueWhenPathIsContained() {
         Routes routes = new Routes();
         routes.add("GET", "/test", (request) -> HttpResponse.ok("OK"));
         assertTrue(routes.contains("/test"));
     }

     @Test
     public void returnFalseWhenPathIsNotContained() {
         Routes routes = new Routes();
         assertFalse(routes.contains("/test"));
     }

     @Test
     public void returnCallbackWhenPathIsContained() {
         Routes routes = new Routes();
         Function<HttpRequest, HttpResponse> callback = (request) -> HttpResponse.ok("OK");
         routes.add("GET", "/test", callback);

         HttpRequest request = new HttpRequest("GET", "/test");
         HttpResponse response = routes.findAndApply(request);

         assertEquals(200, response.statusCode());
     }

     @Test
     public void returnNullWhenPathIsNotContained() {
         Routes routes = new Routes();

         HttpRequest request = new HttpRequest("GET", "/test");
         HttpResponse response = routes.findAndApply(request);

         assertEquals(404, response.statusCode());
     }

     @Test
     public void addMultipleRoutes() {
         Routes routes = new Routes();
         Function<HttpRequest, HttpResponse> callback1 = (request) -> HttpResponse.ok("OK");
         Function<HttpRequest, HttpResponse> callback2 = (request) -> HttpResponse.created("Created");
         routes.add("GET", "/test", callback1);
         routes.add("GET", "/test2", callback2);
         assertTrue(routes.contains("/test"));
         assertTrue(routes.contains("/test2"));
         HttpRequest request1 = new HttpRequest("GET", "/test");
         HttpResponse response1 = routes.findAndApply(request1);
         assertEquals(200, response1.statusCode());
         HttpRequest request2 = new HttpRequest("GET", "/test2");
         HttpResponse response2 = routes.findAndApply(request2);
         assertEquals(201, response2.statusCode());
     }
}
