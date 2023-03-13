import api.client.OrdersClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.ListOrder;
import org.junit.Test;

@DisplayName("Список заказов")
public class ListOrderTests extends BaseTest {

    ListOrder listOrder = new ListOrder();

    @Test
    @DisplayName("Список заказов возвращается")
    public void getListOrder() {

        OrdersClient ordersClient = new OrdersClient();
        Response response = ordersClient.checkGetListOrderApi(listOrder);
        response.then().statusCode(200);
    }
}
