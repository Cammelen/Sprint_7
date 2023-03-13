import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Логин существующего курьера")
public class ExistingCourierTests extends BaseTest {

    Courier existingCourier = new Courier("Jako78", "1111", "Jan");
    Courier existingCourierOnceLogin = new Courier("Jako78", "");
    Courier existingCourierOncePassword = new Courier("", "1111");
    Courier notCorrectExistingCourierLogin = new Courier("Jakooooo", "1111");
    Courier notCorrectExistingCourierPassword = new Courier("Jako78", "0000");

    @Test
    @DisplayName("Успешная авторизация курьера")
    public void authorizationCourier() {

        Response response = courierClient.checkLoginCourierApi(existingCourier);
        response.then().statusCode(200);
        response.then().assertThat().body("id", equalTo(157676));
    }

    @Test
    @DisplayName("Нельзя авторизоваться без пароля")
    public void authorizationCourierOnceLogin() {

        Response response = courierClient.checkLoginCourierApi(existingCourierOnceLogin);
        response.then().statusCode(400);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Нельзя авторизоваться без логина")
    public void authorizationCourierOncePassword() {

        Response response = courierClient.checkLoginCourierApi(existingCourierOncePassword);
        response.then().statusCode(400);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Нельзя авторизоваться под несуществующим курьером/некорректным логином")
    public void authorizationCourierNotCorrectLogin() {

        Response response = courierClient.checkLoginCourierApi(notCorrectExistingCourierLogin);
        response.then().statusCode(404);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Нельзя авторизоваться с некорректным паролем")
    public void authorizationCourierNotCorrectPassword() {

        Response response = courierClient.checkLoginCourierApi(notCorrectExistingCourierPassword);
        response.then().statusCode(404);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}
