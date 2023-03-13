package api.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class CourierClient extends BaseClient {

    final String POST_COURIER = "/api/v1/courier";
    final String POST_LOGIN_COURIER = "/api/v1/courier/login";
    final String DELETE_COURIER = "/api/v1/courier/";

    @Step("Создание курьера")
    public Response checkCreateCourierApi(Object body) {
        return doPostRequest(POST_COURIER, body);
    }

    @Step("Авторизация курьера")
    public Response checkLoginCourierApi(Object body) {
        return doPostRequest(POST_LOGIN_COURIER, body);
    }

    @Step("Удаление курьера")
    public Response checkDeleteCourierApi(Long id) {
        return doDeleteRequest(DELETE_COURIER + id);
    }
}
