package edu.practikum.sprint7.courierTests;

import edu.practikum.sprint7.models.Courier;
import edu.practikum.sprint7.models.CourierCreds;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static edu.practikum.sprint7.models.CourierCreds.*;
import static edu.practikum.sprint7.utils.Utils.randomString;
import static org.apache.http.HttpStatus.*;
import static edu.practikum.sprint7.courier.CourierGenerator.randomCourier;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LoginCourierTests extends CourierBeforeAndAfter {

    Courier courier = randomCourier();

    @Before
    public void courierCreate() {
        courierClient.create(courier);
    }

    @Test
    @DisplayName("Успешная авторизация курьером при передаче обязательных полей")
    @Description("Курьер может авторизоваться (200), успешный запрос возвращает 'id'")
    public void loginWithCorrectData() {
        Response loginResponse = courierClient.login(fromCourier(courier));
        courierId = loginResponse.path("id").toString();
        assertEquals("Ошибка при авторизации", SC_OK, loginResponse.statusCode());
        assertNotNull(loginResponse.path("id"));
    }

    @Test
    @DisplayName("Ошибка при попытке авторизации без логина")
    @Description("Запрос возвращает ошибку (400), сообщение о недостатке данных")
    public void loginWithoutLogin() {
        Response loginResponse = courierClient.login(customLoginCourier(null, courier));
        assertEquals("Неправильный код ответа", SC_BAD_REQUEST, loginResponse.statusCode());
        assertEquals("Некорректное сообщение об ошибке",LOGIN_400_MESSAGE, loginResponse.path("message"));
    }
    @Test
    @DisplayName("Ошибка при попытке авторизации без пароля")
    @Description("Запрос возвращает ошибку (400), сообщение о недостатке данных")
    public void loginWithoutPassword() {
        Response loginResponse = courierClient.login(customPasswordCourier(courier, null));
        assertEquals("Неправильный код ответа", SC_BAD_REQUEST, loginResponse.statusCode());
        assertEquals("Некорректное сообщение об ошибке",LOGIN_400_MESSAGE, loginResponse.path("message"));
    }

    @Test
    @DisplayName("Ошибка при попытке авторизации с неверным логином")
    @Description("Запрос возвращает ошибку (404), сообщение, что учетная запись не найдена")
    public void loginWithIncorrectLogin(){
        Response loginResponse = courierClient.login(customLoginCourier(randomString(10), courier));
        assertEquals("Неправильный код ответа", SC_NOT_FOUND, loginResponse.statusCode());
        assertEquals("Некорректное сообщение об ошибке",LOGIN_404_MESSAGE, loginResponse.path("message"));
    }

    @Test
    @DisplayName("Ошибка при попытке авторизации с неверным паролем")
    @Description("Запрос возвращает ошибку (404), сообщение, что учетная запись не найдена")
    public void loginWithIncorrectPassword(){
        Response loginResponse = courierClient.login(customPasswordCourier(courier, randomString(10)));
        assertEquals("Неправильный код ответа", SC_NOT_FOUND, loginResponse.statusCode());
        assertEquals("Некорректное сообщение об ошибке",LOGIN_404_MESSAGE, loginResponse.path("message"));
    }

    @Test
    @DisplayName("Ошибка при попытке авторизации несуществующим пользователем")
    @Description("Запрос возвращает ошибку (404), сообщение, что учетная запись не найдена")
    public void loginWithFullIncorrectData(){
        CourierCreds courierCreds = new CourierCreds(randomString(10), randomString(13));
        Response loginResponse = courierClient.login(courierCreds);
        assertEquals("Неправильный код ответа", SC_NOT_FOUND, loginResponse.statusCode());
        assertEquals("Некорректное сообщение об ошибке",LOGIN_404_MESSAGE, loginResponse.path("message"));
    }
}
