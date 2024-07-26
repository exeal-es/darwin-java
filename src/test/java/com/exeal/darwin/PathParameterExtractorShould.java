package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PathParameterExtractorShould {
    @Test
    public void extract_path_param() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{id}", "/test/123");
        assertEquals("123", extractor.extractParam("id"));
    }

    @Test
    public void extract_path_param2() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{id}", "/test/456");
        assertEquals("456", extractor.extractParam("id"));
    }

    @Test
    public void extract_path_param3() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{name}", "/test/abc");
        assertEquals("abc", extractor.extractParam("name"));
    }

    @Test
    public void extract_path_param4() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{name}/foo/{id}", "/test/abc/foo/123");
        assertEquals("abc", extractor.extractParam("name"));
        assertEquals("123", extractor.extractParam("id"));
    }

    @Test
    public void return_null_when_param_is_not_present() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{name}", "/test/abc");
        assertNull(extractor.extractParam("doesNotExist"));
    }
}
