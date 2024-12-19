package com.exeal.darwin;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.HashMap;
import java.util.Map;

public class AuthorizationHeaderParser {

    public static ParameterBag parseAuthorizationHeaderAndReturnClaims(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid Authorization header");
        }

        String token = authorizationHeader.substring(7);
        DecodedJWT jwt = JWT.decode(token);

        Map<String, String> claimsMap = new HashMap<>();
        jwt.getClaims().forEach((key, value) -> claimsMap.put(key, value.asString()));

        return new ParameterBag(claimsMap);
    }
}
