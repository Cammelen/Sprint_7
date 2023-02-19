import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.ListOrder;
import org.junit.Test;
import static io.restassured.RestAssured.given;

@DisplayName("Список заказов")
public class ListOrderTests extends Steps {


    private final Steps steps = new Steps();

    ListOrder listOrder = new ListOrder();

    @Test
    @DisplayName("Список заказов возвращается")
    public void getListOrder() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(listOrder)
                .get("/api/v1/orders");
        steps.checkStatusCode200(response);
        steps.printResponseBody(response);
    }
}
