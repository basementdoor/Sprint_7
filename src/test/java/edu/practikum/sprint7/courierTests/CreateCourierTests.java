package edu.practikum.sprint7.courierTests;

import edu.practikum.sprint7.models.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static edu.practikum.sprint7.courier.CourierGenerator.*;
import static edu.practikum.sprint7.models.CourierCreds.fromCourier;
import static edu.practikum.sprint7.utils.Utils.randomString;
import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;

public class CreateCourierTests extends CourierBeforeAndAfter {

    @Test
    @DisplayName("Успешное создание курьера")
    @Description("Ожидаем код 200, тело 'ok: true'")
    public void createValidCourier() {

        Courier courier = randomCourier();
        Response response = courierClient.create(courier);

        assertEquals("Неверный статус код", SC_CREATED, response.statusCode());
        assertEquals("Неверное тело ответа", true, response.path("ok"));

        Response loginResponse = courierClient.login(fromCourier(courier));
        courierId = loginResponse.path("id").toString();

        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
    }

    @Test
    @DisplayName("Создание двух одинаковых курьеров невозможно")
    @Description("Ожидаем код 409, сообщение, что логин уже используется")
    public void createSameCourier409() {
        Courier courier = randomCourier();
        courierClient.create(courier);
        //ниже минимальная проверка - убедиться, что курьер был создан + получение id для удаления
        Response loginResponse = courierClient.login(fromCourier(courier));
        courierId = loginResponse.path("id").toString();
        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());

        Courier sameCourier = new Courier()
                .withLogin(courier.getLogin())
                .withPassword(courier.getPassword())
                .withFirstName(courier.getFirstName());

        Response sameCourierResponse = courierClient.create(sameCourier);
        assertEquals("Код не соотвествует ожидаемому 409"
                , SC_CONFLICT, sameCourierResponse.statusCode());
        assertEquals("Неверное сообщение об ошибке"
                , COURIER_409_MESSAGE, sameCourierResponse.path("message"));
    }

    @Test
    @DisplayName("Создание двух курьеров с одинаковым логином невозможно")
    @Description("Ожидаем код 409, сообщение, что логин уже используется")
    public void createCourierWithSameLogin409() {
        Courier courier = randomCourier();
        courierClient.create(courier);
        //ниже минимальная проверка - убедиться, что курьер был создан + получение id для удаления
        Response loginResponse = courierClient.login(fromCourier(courier));
        courierId = loginResponse.path("id").toString();
        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());

        Courier sameLoginCourier = new Courier()
                .withLogin(courier.getLogin())
                .withPassword(randomString(12))
                .withFirstName(randomString(10));

        Response sameCourierResponse = courierClient.create(sameLoginCourier);
        assertEquals("Код не соотвествует ожидаемому 409"
                , SC_CONFLICT, sameCourierResponse.statusCode());
        assertEquals("Неверное сообщение об ошибке"
                , COURIER_409_MESSAGE, sameCourierResponse.path("message"));
    }

    @Test
    @DisplayName("Создание курьера без передачи логина в ручку")
    @Description("Ожидаем код 400, сообщение, недостаточно данных для создания учетной записи")
    public void createCourierWithoutLogin400() {
        Courier courier = courierWithoutLogin();
        Response response = courierClient.create(courier);
        assertEquals("Код не соотвествует ожидаемому 400"
                , SC_BAD_REQUEST, response.statusCode());
        assertEquals("Неверное сообщение об ошибке"
                , COURIER_400_MESSAGE, response.path("message"));
    }

    @Test
    @DisplayName("Создание курьера без передачи пароля в ручку")
    @Description("Ожидаем код 400, сообщение, недостаточно данных для создания учетной записи")
    public void createCourierWithoutPassword400() {
        Courier courier = courierWithoutPassword();
        Response response = courierClient.create(courier);
        assertEquals("Код не соотвествует ожидаемому 400"
                , SC_BAD_REQUEST, response.statusCode());
        assertEquals("Неверное сообщение об ошибке"
                , COURIER_400_MESSAGE, response.path("message"));
    }
}
