import api.client.CourierClient;
import io.restassured.RestAssured;
import org.junit.Before;

public class BaseTest {

    CourierClient courierClient = new CourierClient();

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }
}
