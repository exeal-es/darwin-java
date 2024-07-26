package com.exeal.darwin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record PathParameterExtractor(String pathTemplate, String path) {
    public String extractParam(String name) {
        String regex = pathTemplate.replaceAll("\\{(?<"+ name+ ">\\w+)}", "(?<${"+ name+ "}>\\\\w+)");
        try {
            Matcher matcher = Pattern.compile(regex).matcher(path);
            return matcher.replaceAll("${"+ name+ "}");
        } catch (Exception e) {
            return null;
        }
    }
}