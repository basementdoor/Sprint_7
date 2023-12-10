package edu.practikum.sprint7.courier;

import edu.practikum.sprint7.models.Courier;
import edu.practikum.sprint7.models.CourierCreds;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {

    private static final String COURIER_URL = "/api/v1/courier";
    private static final String COURIER_LOGIN_URL = "/api/v1/courier/login";

    private static final String COURIER_DELETE_URL = "/api/v1/courier/";
    @Step("Создание курьера {courier}")
    public Response create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post(COURIER_URL);
    }
    @Step("Авторизация курьера с кредами {courierCred}")
    public Response login(CourierCreds courierCreds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(courierCreds)
                .when()
                .post(COURIER_LOGIN_URL);

    }

    @Step("Удаление курьера с ID {courierDelete}")
    public Response delete(String courierId) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .when()
                .delete(COURIER_DELETE_URL+courierId);
    }
}
