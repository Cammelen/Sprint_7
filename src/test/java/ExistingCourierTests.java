import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Логин существующего курьера")
public class ExistingCourierTests extends Steps {

    private final Steps steps = new Steps();

    Courier existingCourier = new Courier("Jako78", "1111", "Jan");
    Courier existingCourierOnceLogin = new Courier("Jako78", "");
    Courier existingCourierOncePassword = new Courier("", "1111");
    Courier notCorrectExistingCourierLogin = new Courier("Jakooooo", "1111");
    Courier notCorrectExistingCourierPassword = new Courier("Jako78", "0000");

    @Test
    @DisplayName("Успешная авторизация курьера")
    public void authorizationCourier() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(existingCourier)
                .post("/api/v1/courier/login");
        steps.checkStatusCode200(response);
        response.then().assertThat().body("id", equalTo(157676));
        System.out.println(existingCourier.getLogin());
        System.out.println(existingCourier.getPassword());
        System.out.println(existingCourier.getFirstName());
        steps.printResponseBody(response);
    }

    @Test
    @DisplayName("Нельзя авторизоваться без пароля")
    public void authorizationCourierOnceLogin() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(existingCourierOnceLogin)
                .post("/api/v1/courier/login");
        steps.checkStatusCode400(response);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
        System.out.println(existingCourierOnceLogin.getLogin());
        System.out.println(existingCourierOnceLogin.getPassword());
        steps.printResponseBody(response);
    }

    @Test
    @DisplayName("Нельзя авторизоваться без логина")
    public void authorizationCourierOncePassword() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(existingCourierOncePassword)
                .post("/api/v1/courier/login");
        steps.checkStatusCode400(response);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
        System.out.println(existingCourierOncePassword.getLogin());
        System.out.println(existingCourierOncePassword.getPassword());
        steps.printResponseBody(response);
    }

    @Test
    @DisplayName("Нельзя авторизоваться под несуществующим курьером/некорректным логином")
    public void authorizationCourierNotCorrectLogin() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(notCorrectExistingCourierLogin)
                .post("/api/v1/courier/login");
        steps.checkStatusCode404(response);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));
        System.out.println(notCorrectExistingCourierLogin.getLogin());
        System.out.println(notCorrectExistingCourierLogin.getPassword());
        steps.printResponseBody(response);
    }

    @Test
    @DisplayName("Нельзя авторизоваться с некорректноым паролем")
    public void authorizationCourierNotCorrectPassword() {
        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .body(notCorrectExistingCourierPassword)
                .post("/api/v1/courier/login");
        steps.checkStatusCode404(response);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));
        System.out.println(notCorrectExistingCourierPassword.getLogin());
        System.out.println(notCorrectExistingCourierPassword.getPassword());
        steps.printResponseBody(response);
    }
}
