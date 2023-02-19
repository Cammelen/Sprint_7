import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import org.junit.Test;
import java.util.Random;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Создание курьера")
public class CreateCourierTests extends Steps {

    private final Steps steps = new Steps();

    Courier courier = new Courier("Jako" + new Random().nextInt(1000), "1111", "Jan");
    Courier courierWithoutFirstName = new Courier("Jako" + new Random().nextInt(1000), "1111");
    Courier courierOnceLogin = new Courier("Jako" + new Random().nextInt(1000));
    Courier courierOncePassword = new Courier("1111");

    @Test
    @DisplayName("Успешное создание нового курьера")
    public void createCourier() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(courier)
                .post("/api/v1/courier");
        steps.checkStatusCode201(response);
        response.then().assertThat().body("ok", equalTo(true));
        System.out.println(courier.getLogin());
        System.out.println(courier.getPassword());
        System.out.println(courier.getFirstName());
        System.out.println(courier.getId());
        steps.printResponseBody(response);
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void createExistCourier() {
        given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(courier)
                .post("/api/v1/courier");

        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(courier)
                .post("/api/v1/courier");
        steps.checkStatusCode409(response);
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
        System.out.println(courier.getLogin());
        steps.printResponseBody(response);
    }

    @Test
    @DisplayName("Успешное создание курьера без имени")
    public void createCourierWithoutFirstName() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(courierWithoutFirstName)
                .post("/api/v1/courier");
        steps.checkStatusCode201(response);
        response.then().assertThat().body("ok", equalTo(true));
        System.out.println(courierWithoutFirstName.getLogin());
        System.out.println(courierWithoutFirstName.getPassword());
        System.out.println(courierWithoutFirstName.getFirstName());
        steps.printResponseBody(response);
    }

    @Test
    @DisplayName("Нельзя создать курьера без пароля")
    public void createCourierOnceLogin() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(courierOnceLogin)
                .post("/api/v1/courier");
        steps.checkStatusCode400(response);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
        System.out.println(courierOnceLogin.getLogin());
        steps.printResponseBody(response);
    }

    @Test
    @DisplayName("Нельзя создать курьера без логина")
    public void createCourierOncePassword() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(courierOncePassword)
                .post("/api/v1/courier");
        steps.checkStatusCode400(response);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
        System.out.println(courierOncePassword.getLogin());
        steps.printResponseBody(response);
    }
}