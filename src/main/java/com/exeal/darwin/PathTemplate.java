package com.exeal.darwin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record PathTemplate(String value) {

    public boolean matches(String path) {
        String regex = value.replaceAll("\\{\\w+\\}", "\\\\w+");
        return path.matches(regex);
    }

    public String extractParam(String path, String name) {
        String regex = value.replaceAll("\\{(?<"+ name+ ">\\w+)}", "(?<${"+ name+ "}>\\\\w+)");
        try {
            Matcher matcher = Pattern.compile(regex).matcher(path);
            return matcher.replaceAll("${"+ name+ "}");
        } catch (Exception e) {
            return null;
        }
    }
}
