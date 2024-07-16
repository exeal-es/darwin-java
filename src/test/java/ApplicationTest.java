import com.exeal.darwin.Main;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

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
}
