import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.Courier;
import model.CourierResponse;
import org.junit.After;
import org.junit.Test;
import java.util.Random;
import static org.hamcrest.Matchers.equalTo;

@DisplayName("Создание курьера")
public class CreateCourierTests extends BaseTest {

    Courier courier = new Courier("Jako" + new Random().nextInt(100000), "1111", "Jan");
    Courier courierWithoutFirstName = new Courier("Jako" + new Random().nextInt(100000), "1111");
    Courier courierOnceLogin = new Courier("Jako" + new Random().nextInt(100000));
    Courier courierOncePassword = new Courier("1111");

    @Test
    @DisplayName("Успешное создание нового курьера")
    public void createCourier() {

        Response response = courierClient.checkCreateCourierApi(courier);
        response.then().statusCode(201);
        response.then().assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать двух одинаковых курьеров")
    public void createExistCourier() {

        courierClient.checkCreateCourierApi(courier);
        Response response = courierClient.checkCreateCourierApi(courier);
        response.then().statusCode(409);
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Успешное создание курьера без имени")
    public void createCourierWithoutFirstName() {

        Response response = courierClient.checkCreateCourierApi(courierWithoutFirstName);
        response.then().statusCode(201);
        response.then().assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Нельзя создать курьера без пароля")
    public void createCourierOnceLogin() {

        Response response = courierClient.checkCreateCourierApi(courierOnceLogin);
        response.then().statusCode(400);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Нельзя создать курьера без логина")
    public void createCourierOncePassword() {

        Response response = courierClient.checkCreateCourierApi(courierOncePassword);
        response.then().statusCode(400);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @After
    public void cleanUp() {

        CourierResponse courierResponse = courierClient.checkLoginCourierApi(courier).as(CourierResponse.class);
        Long idCourier = courierResponse.getId();
        if (idCourier != null) {
            courierClient.checkDeleteCourierApi(idCourier);
        }

        CourierResponse courierResponseWithoutFirstName = courierClient.checkLoginCourierApi(courierWithoutFirstName).as(CourierResponse.class);
        Long idCourierWithoutFirstName = courierResponseWithoutFirstName.getId();
        if (idCourierWithoutFirstName != null) {
            courierClient.checkDeleteCourierApi(idCourierWithoutFirstName);
        }
    }
}




