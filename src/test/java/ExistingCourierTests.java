import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import org.junit.Test;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Логин существующего курьера")
public class ExistingCourierTests extends BaseTest {

    private BaseTest steps = new BaseTest();

    Courier existingCourier = new Courier("Jako78", "1111", "Jan");
    Courier existingCourierOnceLogin = new Courier("Jako78", "");
    Courier existingCourierOncePassword = new Courier("", "1111");
    Courier notCorrectExistingCourierLogin = new Courier("Jakooooo", "1111");
    Courier notCorrectExistingCourierPassword = new Courier("Jako78", "0000");

    @Test
    @DisplayName("Успешная авторизация курьера")
    public void authorizationCourier() {
        Response response = courierClient.checkLoginCourierApi(existingCourier);
        System.out.println(existingCourier.getLogin());
        System.out.println(existingCourier.getPassword());
        System.out.println(existingCourier.getFirstName());
        steps.checkStatusCode200(response);
        steps.printResponseBody(response);
        response.then().assertThat().body("id", equalTo(157676));
    }

    @Test
    @DisplayName("Нельзя авторизоваться без пароля")
    public void authorizationCourierOnceLogin() {
        Response response = courierClient.checkLoginCourierApi(existingCourierOnceLogin);
        System.out.println(existingCourierOnceLogin.getLogin());
        System.out.println(existingCourierOnceLogin.getPassword());
        steps.checkStatusCode400(response);
        steps.printResponseBody(response);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Нельзя авторизоваться без логина")
    public void authorizationCourierOncePassword() {
        Response response = courierClient.checkLoginCourierApi(existingCourierOncePassword);
        System.out.println(existingCourierOncePassword.getLogin());
        System.out.println(existingCourierOncePassword.getPassword());
        steps.checkStatusCode400(response);
        steps.printResponseBody(response);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Нельзя авторизоваться под несуществующим курьером/некорректным логином")
    public void authorizationCourierNotCorrectLogin() {
        Response response = courierClient.checkLoginCourierApi(notCorrectExistingCourierLogin);
        System.out.println(notCorrectExistingCourierLogin.getLogin());
        System.out.println(notCorrectExistingCourierLogin.getPassword());
        steps.checkStatusCode404(response);
        steps.printResponseBody(response);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Нельзя авторизоваться с некорректным паролем")
    public void authorizationCourierNotCorrectPassword() {
        Response response = courierClient.checkLoginCourierApi(notCorrectExistingCourierPassword);
        System.out.println(notCorrectExistingCourierPassword.getLogin());
        System.out.println(notCorrectExistingCourierPassword.getPassword());
        steps.checkStatusCode404(response);
        steps.printResponseBody(response);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }
}
