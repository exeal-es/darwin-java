package com.exeal.darwin;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record PathParameterExtractor(String pathTemplate, String path) {
    public ParameterBag extractParams() {
        String regex = pathTemplate.replaceAll("\\{(?<name>\\w+)}", "(?<${name}>\\\\w+)");
        Matcher matcher = Pattern.compile(regex).matcher(path);
        var paramNames = matcher.namedGroups().keySet();
        var params = new HashMap<String, String>();
        for (String paramName : paramNames) {
            params.put(paramName, matcher.replaceAll("${"+ paramName + "}"));
        }
        return new ParameterBag(params);
    }
}
