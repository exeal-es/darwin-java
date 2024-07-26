package com.exeal.darwin;

public record PathTemplate(String value) {

    public boolean matches(String path) {
        String regex = value.replaceAll("\\{\\w+\\}", "\\\\w+");
        return path.matches(regex);
    }

    public String extractParam(String path, String name) {
        String regex = value.replaceAll("\\{(?<"+ name+ ">\\w+)}", "(?<${"+ name+ "}>\\\\w+)");
        return path.replaceAll(regex, "${"+ name+ "}");
    }
}
