package api;

import io.zeplin.Fixture.Main;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.apache.commons.lang3.RandomStringUtils.*;

public class ApiTests extends Main {
    @Override
    public void beforeClass() {}
    @Override
    public void beforeMethod() {}
    @Override
    public void afterClass() {}
    private final static String baseUri = "https://reqres.in";
    @Test(description = "GET test")
    public void testGet() {
            given()
                .when()
                .queryParam("page", "2")
                .get(baseUri + "/api/users")
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200)
                .body("page", equalTo(2));
    }
    @Test(description = "POST & PUT test")
    public void testPostPut() {
        String name = randomAlphabetic(6);
        String job = randomAlphabetic(6);
        given()
                .when()
                .queryParam("name", name)
                .queryParam("job", job)
                .header("Content-Type", "application/json")
                .post(baseUri + "/api/users")
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(201);

        given()
                .when()
                .queryParam("name", name)
                .queryParam("job", job + "Edit")
                .header("Content-Type", "application/json")
                .put(baseUri + "/api/users/2")
                .then()
                .log()
                .body()
                .assertThat()
                .statusCode(200);
    }
    @Test(description = "DELETE test")
    public void testDelete() {
        given()
                .when()
                .delete(baseUri + "/api/users/2")
                .then()
                .assertThat()
                .statusCode(204);
    }
}
