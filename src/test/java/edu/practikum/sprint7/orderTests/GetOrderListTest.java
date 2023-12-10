package edu.practikum.sprint7.orderTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GetOrderListTest extends OrderBefore {

    @Test
    @DisplayName("Тест на успешное получение списка заказов")
    @Description("Ожидается код ответа 200, в ответе есть orders")
    public void checkGetListOrders() {
        Response response = orderClient.getOrdersList();
        assertEquals("Код не соотвествует ожидаемому 200", SC_OK, response.statusCode());
        assertNotNull(response.body().path("orders"));
    }
}
