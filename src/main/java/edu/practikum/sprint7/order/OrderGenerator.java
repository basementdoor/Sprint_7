package edu.practikum.sprint7.order;

import edu.practikum.sprint7.models.Order;
import static edu.practikum.sprint7.utils.Utils.randomInt;
import static edu.practikum.sprint7.utils.Utils.randomString;

public class OrderGenerator {

    public static Order getOrderGreyColor(){
        return new Order(
                randomString(6),
                randomString(8),
                "3-я улица Ямского поля, 6",
                "Черкизовская",
                "+79257809781",
                randomInt(),
                "2023-12-24",
                randomString(15),
                new String[]{"GREY"});
    }
    public static Order getOrderBlackColor(){
        return new Order(
                randomString(6),
                randomString(8),
                "Юбилейная, 1",
                "Котельники",
                "+79260272115",
                randomInt(),
                "2023-12-27",
                randomString(15),
                new String[]{"BLACK"});
    }
    public static Order getOrderTwoColors(){
        return new Order(
                randomString(6),
                randomString(8),
                "Одесская, 5",
                "Нахимовский проспект",
                "+79059924427",
                randomInt(),
                "2023-12-29",
                randomString(15),
                new String[]{"GREY", "BLACK"});
    }

    public static Order getOrderNoColors(){
        return new Order(
                randomString(6),
                randomString(8),
                "Проспект Маршала Жукова, 15",
                "Текстильщики",
                "+79157432123",
                randomInt(),
                "2023-12-27",
                randomString(15),
                null);
    }
}
