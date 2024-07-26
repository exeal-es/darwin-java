package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RequestShould {
     @Test
     public void extract_path_param() {
         PathTemplate pathTemplate = new PathTemplate("/test/{id}");
         Request request = new Request(QueryParams.empty(), "/test/123", pathTemplate);
         assertEquals("123", request.pathParam("id"));
     }

     @Test
     public void extract_path_param2() {
         PathTemplate pathTemplate = new PathTemplate("/test/{id}");
         Request request = new Request(QueryParams.empty(), "/test/456", pathTemplate);
         assertEquals("456", request.pathParam("id"));
     }

     @Test
     public void extract_path_param3() {
         PathTemplate pathTemplate = new PathTemplate("/test/{name}");
         Request request = new Request(QueryParams.empty(), "/test/abc", pathTemplate);
         assertEquals("abc", request.pathParam("name"));
     }

    @Test
    public void extract_path_param4() {
        PathTemplate pathTemplate = new PathTemplate("/test/{name}/foo/{id}");

        Request request = new Request(QueryParams.empty(), "/test/abc/foo/123", pathTemplate);

        assertEquals("abc", request.pathParam("name"));
        assertEquals("123", request.pathParam("id"));
    }

    @Test
    public void return_null_when_param_is_not_present() {
        PathTemplate pathTemplate = new PathTemplate("/test/{name}");
        Request request = new Request(QueryParams.empty(), "/test/abc", pathTemplate);
        assertNull(request.pathParam("doesNotExist"));
    }
}