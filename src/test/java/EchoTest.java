
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class EchoTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://postman-echo.com";
    }

        @Test
        public void getTest() {

            given()
                    .queryParam("foo1", "bar1")
                    .queryParam("foo2", "bar2")
                    .when()
                    .get("/get")
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .body("args.foo1", equalTo("bar1"))
                    .body("args.foo2", equalTo("bar2"))
                    .body("headers.host", equalTo("postman-echo.com"))
                    .body("headers.accept", equalTo("*/*"))
                    .body("headers.accept-encoding", equalTo("gzip,deflate"));
        }

    @Test
    public void testPost() {

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", equalTo("This is expected to be sent back as part of response body."))
                .body("headers.content-type", containsString("text/plain"));
    }
@Test
    public void testPostFromData() {

    given()
            .contentType(ContentType.MULTIPART)
            .multiPart("foo1", "bar1")
            .multiPart("foo2", "bar2")
            .when()
            .post("/post")
            .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("form.foo1", equalTo("bar1"))
            .body("form.foo2", equalTo("bar2"))
            .body("headers.content-type", containsString("multipart/form-data"));

    }
    @Test
    public void testPut() {

        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", equalTo(requestBody))
                .body("headers.content-type", containsString("text/plain"));
    }

    @Test
    public void testPatch() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("data", equalTo(requestBody))
                .body("headers.content-type", containsString("text/plain"));
    }

    @Test
    public void testDelete() {
        String requestBody = "This is expected to be sent back as part of response body.";

        given()
                .contentType(ContentType.TEXT)
                .body(requestBody)
                .when()
                .patch("/delete")
                .then()
                .statusCode(404);

    }



}
