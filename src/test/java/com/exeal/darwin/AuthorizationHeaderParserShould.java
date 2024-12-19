package com.exeal.darwin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorizationHeaderParserShould {
    @Test
    void testParseAuthorizationHeaderAndReturnClaims() {
        String authorizationHeader = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IlBlcmljbyIsImlhdCI6MTUxNjIzOTAyMn0.t68XxLxzXDkhpvEsezxzyro51qbaFpXcl9C0Vw8h8do";

        ParameterBag claims = AuthorizationHeaderParser.parseAuthorizationHeaderAndReturnClaims(authorizationHeader);

        assertEquals("Perico", claims.get("name"));
    }

    @Test
    void testParseAuthorizationHeaderAndReturnClaimsAnotherName() {
        String authorizationHeader = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        ParameterBag claims = AuthorizationHeaderParser.parseAuthorizationHeaderAndReturnClaims(authorizationHeader);

        assertEquals("John Doe", claims.get("name"));
    }
}