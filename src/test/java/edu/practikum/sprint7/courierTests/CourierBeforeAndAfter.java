package edu.practikum.sprint7.courierTests;

import edu.practikum.sprint7.courier.CourierClient;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;

public abstract class CourierBeforeAndAfter {

    protected static final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
    //тесты падают из-за несоответствия сообщению ниже, но по документации должно быть таким
    protected static final String COURIER_409_MESSAGE = "Этот логин уже используется";
    protected static final String COURIER_400_MESSAGE = "Недостаточно данных для создания учетной записи";
    protected static final String LOGIN_400_MESSAGE = "Недостаточно данных для входа";
    protected static final String LOGIN_404_MESSAGE = "Учетная запись не найдена";

    protected String courierId;
    CourierClient courierClient = new CourierClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @After
    public void tearDown() {
        courierClient.delete(courierId);
    }

}
