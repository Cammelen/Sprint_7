import api.client.OrdersClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Order;
import org.junit.Test;
import java.util.List;
import static org.hamcrest.Matchers.notNullValue;

@DisplayName("Создание заказа")
public class CreateOrderTests extends BaseTest {

    OrdersClient ordersClient = new OrdersClient();

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

        Response response = ordersClient.checkCreateOrderApi(orderWithoutColor);
        response.then().statusCode(201);
        response.then().assertThat().body("track", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа с двумя цветами")
    public void createOrderColor() {

        Response response = ordersClient.checkCreateOrderApi(orderColor);
        response.then().statusCode(201);
        response.then().assertThat().body("track", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа с одним цветом")
    public void createOrderOneColor() {

        Response response = ordersClient.checkCreateOrderApi(orderOneColor);
        response.then().statusCode(201);
        response.then().assertThat().body("track", notNullValue());
    }
}
