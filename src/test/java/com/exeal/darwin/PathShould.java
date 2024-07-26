package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PathShould {

    @Test
    public void match_entire_path() {
        Path path = new Path("/test");
        assertTrue(path.matches("/test"));
    }

    @Test
    public void match_different_path() {
        Path path = new Path("/test");
        assertFalse(path.matches("/test2"));
    }

    @Test
    public void match_path_with_placeholder() {
        Path path = new Path("/test/{id}");
        assertTrue(path.matches("/test/123"));
    }

    @Test
    public void match_path_with_placeholder2() {
        Path path = new Path("/test/{id}");
        assertTrue(path.matches("/test/456"));
    }

    @Test
    public void match_path_with_placeholder3() {
        Path path = new Path("/test/{name}");
        assertTrue(path.matches("/test/abc"));
    }

    @Test
    public void match_path_with_multiple_placeholders() {
        Path path = new Path("/test/{name}/foo/{id}");
        assertTrue(path.matches("/test/abc/foo/123"));
    }
}
