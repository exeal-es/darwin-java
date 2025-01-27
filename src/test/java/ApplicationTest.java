import com.exeal.darwin.Application;
import com.exeal.darwin.HttpResponseFactory;
import com.exeal.darwin.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.ServerSocket;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ApplicationTest {
    @Test
    public void testResponseStatus404() throws IOException {
        // Arrange
        Application app = new Application();
        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        given()
                .when()
                .get("http://localhost:" + port)
        .then()
                .statusCode(404);
    }

    private int findAvailableTcpPort() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(0)) {
            return serverSocket.getLocalPort();
        }
    }

    @Test
    public void testHelloShouldRespondWith200Ok() throws IOException {
        // Arrange
        Application app = new Application();
        app.get("/hello", (req) -> HttpResponseFactory.ok("Hello!"));

        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        given()
                .when()
                .get("http://localhost:" + port + "/hello")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPassQueryStringParameterAndReturnItInTheBody() throws IOException {
        // Arrange
        Application app = new Application();
        app.get("/greet", (req) -> {
            String name = req.queryParam("name");
            String body = "Hello " + name + "!";
            return HttpResponseFactory.ok(body);
        });

        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        String body = given()
                .when()
                .get("http://localhost:" + port + "/greet?name=Pedro")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertEquals("Hello Pedro!", body);
    }

    @Test
    public void testRequestResourceWithMethodNotConfigured() throws IOException {
        // Arrange
        Application app = new Application();
        app.get("/hello", (req) -> HttpResponseFactory.ok("Hello!"));

        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        given()
                .when()
                .post("http://localhost:" + port + "/hello")
                .then()
                .statusCode(405);
    }

    @Test
    public void testReturnProblemDetailsForForbiddenError() throws IOException {
        // Arrange
        Application app = new Application();

        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        String body = given()
                .when()
                .post("http://localhost:" + port + "/hello")
                .then()
                .contentType("application/problem+json")
                .statusCode(404)
                .extract()
                .body()
                .asString();

        String expected = "{\"title\":\"Not Found\",\"detail\":\"Resource not found\"}";
        assertEquals(expected, body);
    }

    @Test
    public void testSecuredActionWithValidToken() throws IOException {
        // Arrange
        Application app = new Application();
        boolean isSecured = true;
        app.get("/secured", isSecured, (req) -> {
            String name = req.claim("name");
            return HttpResponseFactory.ok("Hello " + name + "!");
        });

        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        String body = given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
                .when()
                .get("http://localhost:" + port + "/secured")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertEquals("Hello John Doe!", body);
    }

    @Test
    public void testSecuredActionWithoutTokenShouldFail() throws IOException {
        // Arrange
        Application app = new Application();
        boolean isSecured = true;
        app.get("/secured", isSecured, (req) -> {
            String name = req.claim("name");
            return HttpResponseFactory.ok("Hello " + name + "!");
        });

        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        given()
                .when()
                .get("http://localhost:" + port + "/secured")
                .then()
                .statusCode(401);
    }

    @Test
    public void testSecuredActionWithValidTokenWithAnotherName() throws IOException {
        // Arrange
        Application app = new Application();
        boolean isSecured = true;
        app.get("/secured", isSecured, (req) -> {
            String name = req.claim("name");
            return HttpResponseFactory.ok("Hello " + name + "!");
        });

        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        String body = given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IlBlcmljbyIsImlhdCI6MTUxNjIzOTAyMn0.t68XxLxzXDkhpvEsezxzyro51qbaFpXcl9C0Vw8h8do")
                .when()
                .get("http://localhost:" + port + "/secured")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertEquals("Hello Perico!", body);
    }

    @Test
    public void testCreateLogWhenRequest() throws IOException {
        // Arrange
        Logger logger = mock(Logger.class);
        Application app = new Application(logger);
        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        given()
                .when()
                .get("http://localhost:" + port)
        .then()
                .statusCode(404);
        verify(logger).log("http_method=GET status=404");
    }


}
