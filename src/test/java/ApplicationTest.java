import com.exeal.darwin.Main;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTest {
    @Test
    public void testResponseStatus404() {
        // Arrange
        Main.main(new String[]{});

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
        Main.main(new String[]{});

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
        Main.main(new String[]{});

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
        Main.main(new String[]{});

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
