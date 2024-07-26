import com.exeal.darwin.Application;
import com.exeal.darwin.HttpResponse;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        app.get("/hello", (req) -> HttpResponse.ok("Hello!"));

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
    public void testPostToHelloShouldRespondWith201Created() throws IOException {
        // Arrange
        Application app = new Application();
        int port = findAvailableTcpPort();
        app.run(port);

        // Act & Assert
        given()
                .when()
                .post("http://localhost:" + port + "/hello")
                .then()
                .statusCode(201);
    }

    @Test
    public void testPassQueryStringParameterAndReturnItInTheBody() throws IOException {
        // Arrange
        Application app = new Application();
        app.get("/greet", (req) -> {
            String name = req.queryParam("name");
            String body = "Hello " + name + "!";
            return HttpResponse.ok(body);
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
}
