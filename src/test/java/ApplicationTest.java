import com.exeal.darwin.Application;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
    @Test
    public void testResponseStatus404() {
        // Arrange
        Application app = new Application();
        app.run();

        // Act & Assert
        given()
                .when()
                .get("http://localhost:8080")
        .then()
                .statusCode(404);
    }

    @Test
    public void testHelloShouldRespondWith200Ok() {
        // Arrange
        Application app = new Application();
        app.run();

        // Act & Assert
        given()
                .when()
                .get("http://localhost:8080/hello")
                .then()
                .statusCode(200);
    }

    @Test
    public void testPostToHelloShouldRespondWith201Created() {
        // Arrange
        Application app = new Application();
        app.run();

        // Act & Assert
        given()
                .when()
                .post("http://localhost:8080/hello")
                .then()
                .statusCode(201);
    }

    @Test
    public void testPassQueryStringParameterAndReturnItInTheBody() {
        // Arrange
        Application app = new Application();
        app.run();

        // Act & Assert
        String body = given()
                .when()
                .get("http://localhost:8080/greet?name=Pedro")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        assertEquals("Hello Pedro!", body);
    }
}
