package edu.practikum.sprint7.orderTests;

import edu.practikum.sprint7.order.OrderClient;
import io.restassured.RestAssured;
import org.junit.Before;

public class OrderBefore {
    protected static final String BASE_URL = "http://qa-scooter.praktikum-services.ru";
    protected OrderClient orderClient;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
        orderClient = new OrderClient();
    }
}
