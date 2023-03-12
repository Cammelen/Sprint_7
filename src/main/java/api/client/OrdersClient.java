package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class OrdersClient extends BaseClient {

    final String ORDERS = "/api/v1/orders";

    @Step("Создание заказа")
    public Response checkCreateOrderApi(Object body) {
        return doPostRequest(ORDERS, body);
    }

    @Step("Получение заказа")
    public Response checkGetListOrderApi(Object body) {
        return doGetRequest(ORDERS, body);
    }
}
