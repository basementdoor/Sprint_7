package edu.practikum.sprint7.order;

import edu.practikum.sprint7.models.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String ORDER_URL = "/api/v1/orders";
    private static final String CANCEL_ORDER_URL = "/api/v1/orders/cancel";

    @Step("Создание заказа")
    public Response create(Order order) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(order)
                .when()
                .post(ORDER_URL);
    }

    @Step("Получение списка заказов курьера")
    public Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(ORDER_URL);
    }

    @Step("Отмена заказа")
    public Response cancel(int trackId){
        return given()
                .header("Content-type", "application/json")
                .and()
                .queryParam("track", trackId)
                .when()
                .put(CANCEL_ORDER_URL);
    }
}
