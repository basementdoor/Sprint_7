package edu.practikum.sprint7.orderTests;

import edu.practikum.sprint7.models.Order;
import edu.practikum.sprint7.order.OrderGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class CreateOrderParamTest extends OrderBefore{
    protected int trackId;
    private Order order;

    public CreateOrderParamTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData(){
        return new Object[][]{
                {OrderGenerator.getOrderBlackColor()},
                {OrderGenerator.getOrderGreyColor()},
                {OrderGenerator.getOrderTwoColors()},
                {OrderGenerator.getOrderNoColors()}
        };
    }

    @Test
    @DisplayName("Тест на успешное создание заказа")
    @Description("Ожидается код ответа 201, в ответе есть track number")
    public void checkCreateOrder() {

        Response response = orderClient.create(order);
        trackId = response.body().path("track");
        assertEquals("Код не соотвествует ожидаемому", SC_CREATED, response.statusCode());
        assertNotNull(response.path("track"));
    }

    @After
    public void tearDown(){
        orderClient.cancel(trackId);
    }
}
