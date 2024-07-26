package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PathTemplateShould {

    @Test
    public void match_entire_path() {
        PathTemplate pathTemplate = new PathTemplate("/test");
        assertTrue(pathTemplate.matches("/test"));
    }

    @Test
    public void match_different_path() {
        PathTemplate pathTemplate = new PathTemplate("/test");
        assertFalse(pathTemplate.matches("/test2"));
    }

    @Test
    public void match_path_with_placeholder() {
        PathTemplate pathTemplate = new PathTemplate("/test/{id}");
        assertTrue(pathTemplate.matches("/test/123"));
    }

    @Test
    public void match_path_with_placeholder2() {
        PathTemplate pathTemplate = new PathTemplate("/test/{id}");
        assertTrue(pathTemplate.matches("/test/456"));
    }

    @Test
    public void match_path_with_placeholder3() {
        PathTemplate pathTemplate = new PathTemplate("/test/{name}");
        assertTrue(pathTemplate.matches("/test/abc"));
    }

    @Test
    public void match_path_with_multiple_placeholders() {
        PathTemplate pathTemplate = new PathTemplate("/test/{name}/foo/{id}");
        assertTrue(pathTemplate.matches("/test/abc/foo/123"));
    }
}
