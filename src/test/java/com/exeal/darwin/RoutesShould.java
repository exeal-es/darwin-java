package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoutesShould {
     @Test
     public void returnTrueWhenPathIsContained() {
         Routes routes = new Routes();
         routes.put("/test", (request) -> HttpResponse.ok("OK"));
         assertTrue(routes.containsKey("/test"));
     }

     @Test
     public void returnFalseWhenPathIsNotContained() {
         Routes routes = new Routes();
         assertFalse(routes.containsKey("/test"));
     }

     @Test
     public void returnCallbackWhenPathIsContained() {
         Routes routes = new Routes();
         Function<HttpRequest, HttpResponse> callback = (request) -> HttpResponse.ok("OK");
         routes.put("/test", callback);
         assertEquals(callback, routes.get("/test"));
     }

     @Test
     public void returnNullWhenPathIsNotContained() {
         Routes routes = new Routes();
         assertNull(routes.get("/test"));
     }

     @Test
     public void addMultipleRoutes() {
         Routes routes = new Routes();
         Function<HttpRequest, HttpResponse> callback1 = (request) -> HttpResponse.ok("OK");
         Function<HttpRequest, HttpResponse> callback2 = (request) -> HttpResponse.created("Created");
         routes.put("/test", callback1);
         routes.put("/test2", callback2);
         assertTrue(routes.containsKey("/test"));
         assertTrue(routes.containsKey("/test2"));
         assertEquals(callback1, routes.get("/test"));
         assertEquals(callback2, routes.get("/test2"));
     }
}