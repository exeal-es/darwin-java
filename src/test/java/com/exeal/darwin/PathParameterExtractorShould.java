package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PathParameterExtractorShould {
    @Test
    public void extract_path_param() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{id}", "/test/123");
        var params = extractor.extractParams();
        assertEquals("123", params.get("id"));
    }

    @Test
    public void extract_path_param2() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{id}", "/test/456");
        var params = extractor.extractParams();
        assertEquals("456", params.get("id"));
    }

    @Test
    public void extract_path_param3() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{name}", "/test/abc");
        var params = extractor.extractParams();
        assertEquals("abc", params.get("name"));
    }

    @Test
    public void extract_path_param4() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{name}/foo/{id}", "/test/abc/foo/123");
        var params = extractor.extractParams();
        assertEquals("abc", params.get("name"));
        assertEquals("123", params.get("id"));
    }

    @Test
    public void return_null_when_param_is_not_present() {
        PathParameterExtractor extractor = new PathParameterExtractor("/test/{name}", "/test/abc");
        var params = extractor.extractParams();
        assertNull(params.get("doesNotExist"));
    }
}
