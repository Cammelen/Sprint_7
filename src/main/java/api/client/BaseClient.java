package api.client;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BaseClient {

    private String JSON = "application/json";
    private String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2EwNTlmM2QzYjg2YTAwM2Q2ODJmNzYiLCJpYXQiOjE2NzY0NzY5OTgsImV4cCI6MTY3NzA4MTc5OH0.eIBvdlijIzWe0giUUWy2hy2C6CHgjb4MGEmnLYxep5E";

    protected Response doPostRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .auth().oauth2(bearerToken)
                .body(body)
                .post(uri);
    }

    protected Response doGetRequest(String uri, Object body) {
        return given()
                .header("Content-Type", JSON)
                .auth().oauth2(bearerToken)
                .body(body)
                .get(uri);
    }

    protected Response doDeleteRequest(String uri) {
        return given()
                .header("Content-Type", JSON)
                .auth().oauth2(bearerToken)
                .delete(uri);
    }
}
