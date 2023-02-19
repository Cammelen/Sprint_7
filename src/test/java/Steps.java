import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

public class Steps {

    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2M2EwNTlmM2QzYjg2YTAwM2Q2ODJmNzYiLCJpYXQiOjE2NzY0NzY5OTgsImV4cCI6MTY3NzA4MTc5OH0.eIBvdlijIzWe0giUUWy2hy2C6CHgjb4MGEmnLYxep5E";

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void cleanUp() {
    }

    public void printResponseBody(Response response) {
        System.out.println(response.body().asString());
    }

    public void checkStatusCode200(Response response) {
        response.then().statusCode(200);
    }

    public void checkStatusCode201(Response response) {
        response.then().statusCode(201);
    }

    public void checkStatusCode400(Response response) {
        response.then().statusCode(400);
    }

    public void checkStatusCode404(Response response) {
        response.then().statusCode(404);
    }

    public void checkStatusCode409(Response response) {
        response.then().statusCode(409);
    }
}
