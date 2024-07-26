package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourcesShould {
     @Test
     public void returnCallbackWhenPathIsContained() {
         Resources resources = new Resources();
         Function<HttpRequest, HttpResponse> callback = (request) -> HttpResponse.ok("OK");
         resources.add(HttpVerb.GET, new PathTemplate("/test"), new Handler(callback));

         HttpRequest request = new HttpRequest(HttpVerb.GET, "/test");
         HttpResponse response = resources.findAndApply(request);

         assertEquals(200, response.statusCode());
     }

     @Test
     public void returnNotFoundWhenPathIsNotContained() {
         Resources resources = new Resources();

         HttpRequest request = new HttpRequest(HttpVerb.GET, "/test");
         HttpResponse response = resources.findAndApply(request);

         assertEquals(404, response.statusCode());
     }

    @Test
    public void returnMethodNotAllowedWhenPathIsContainedButForOtherMethod() {
        Resources resources = new Resources();
        resources.add(HttpVerb.GET, new PathTemplate("/test"), new Handler((req) -> HttpResponse.ok("OK")));

        HttpResponse response = resources.findAndApply(new HttpRequest(HttpVerb.POST, "/test"));

        assertEquals(405, response.statusCode());
    }

    @Test
    public void returnMethodNotAllowedWhenPathIsContainedButForOtherMethod2() {
        Resources resources = new Resources();
        resources.add(HttpVerb.POST, new PathTemplate("/test"), new Handler((req) -> HttpResponse.ok("OK")));

        HttpResponse response = resources.findAndApply(new HttpRequest(HttpVerb.GET, "/test"));

        assertEquals(405, response.statusCode());
    }

     @Test
     public void addMultipleRoutes() {
         Resources resources = new Resources();
         Function<HttpRequest, HttpResponse> callback1 = (request) -> HttpResponse.ok("OK");
         Function<HttpRequest, HttpResponse> callback2 = (request) -> HttpResponse.created("Created");
         resources.add(HttpVerb.GET, new PathTemplate("/test"), new Handler(callback1));
         resources.add(HttpVerb.GET, new PathTemplate("/test2"), new Handler(callback2));
         HttpRequest request1 = new HttpRequest(HttpVerb.GET, "/test");
         HttpResponse response1 = resources.findAndApply(request1);
         assertEquals(200, response1.statusCode());
         HttpRequest request2 = new HttpRequest(HttpVerb.GET, "/test2");
         HttpResponse response2 = resources.findAndApply(request2);
         assertEquals(201, response2.statusCode());
     }

    @Test
    public void testReturnError500WhenUnhandledExceptionHappensInUserCode() {
        Resources resources = new Resources();
        resources.add(HttpVerb.GET, new PathTemplate("/test"), new Handler((req) -> {
            throw new RuntimeException("Error");
        }));

        HttpResponse response = resources.findAndApply(new HttpRequest(HttpVerb.GET, "/test"));

        assertEquals(500, response.statusCode());
        assertEquals("Error", response.body());
    }

    @Test
    public void parsePathParams() {
        Resources resources = new Resources();
        resources.add(HttpVerb.GET, new PathTemplate("/test/{id}"), new Handler((req) -> HttpResponse.ok(req.pathParam("id"))));

        HttpResponse response = resources.findAndApply(new HttpRequest(HttpVerb.GET, "/test/123"));

        assertEquals(200, response.statusCode());
        assertEquals("123", response.body());
    }
}
