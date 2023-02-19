import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Order;
import org.junit.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Создание заказа")
public class CreateOrderTests extends Steps {

    private final Steps steps = new Steps();

    Order orderWithoutColor = new Order("Bro", "French", "Tokiyskiy, 55",
            4, "+7123456789", 5, "2023-02-23",
            "Jan, come back to Tokiyskiy");

    Order orderColor = new Order("Bro", "French", "Tokiyskiy, 55",
            4, "+7123456789", 5, "2023-02-23",
            "Jan, come back to Tokiyskiy", List.of("BLACK", "GREY"));

    Order orderOneColor = new Order("Bro", "French", "Tokiyskiy, 55",
            4, "+7123456789", 5, "2023-02-23",
            "Jan, come back to Tokiyskiy", List.of("BLACK"));

    @Test
    @DisplayName("Создание заказа без цвета")
    public void createOrderWithoutColor() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(orderWithoutColor)
                .post("/api/v1/orders");
        steps.checkStatusCode201(response);
        response.then().assertThat().body("track", notNullValue());
        steps.printResponseBody(response);
        System.out.println(orderWithoutColor.getColor());
    }

    @Test
    @DisplayName("Создание заказа с двумя цветами")
    public void createOrderColor() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(orderColor)
                .post("/api/v1/orders");
        steps.checkStatusCode201(response);
        response.then().assertThat().body("track", notNullValue());
        steps.printResponseBody(response);
        System.out.println(orderColor.getColor());
    }

    @Test
    @DisplayName("Создание заказа с одним цветом")

    public void createOrderOneColor() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(orderOneColor)
                .post("/api/v1/orders");
        steps.checkStatusCode201(response);
        response.then().assertThat().body("track", notNullValue());
        steps.printResponseBody(response);
        System.out.println(orderOneColor.getColor());
    }
}
