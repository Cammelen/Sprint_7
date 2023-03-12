import api.client.CourierClient;
import api.client.OrdersClient;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.Courier;
import model.CourierResponse;
import org.junit.After;
import org.junit.Before;

import java.util.Random;

import static io.restassured.RestAssured.given;

public class BaseTest {

    CourierClient courierClient = new CourierClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Step
    public void printResponseBody(Response response) {
        System.out.println(response.body().asString());
    }

    @Step
    public void checkStatusCode200(Response response) {
        response.then().statusCode(200);
    }

    @Step
    public void checkStatusCode201(Response response) {
        response.then().statusCode(201);
    }

    @Step
    public void checkStatusCode400(Response response) {
        response.then().statusCode(400);
    }

    @Step
    public void checkStatusCode404(Response response) {
        response.then().statusCode(404);
    }

    @Step
    public void checkStatusCode409(Response response) {
        response.then().statusCode(409);
    }
}
