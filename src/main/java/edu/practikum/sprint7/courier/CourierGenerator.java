package edu.practikum.sprint7.courier;

import edu.practikum.sprint7.models.Courier;

import static edu.practikum.sprint7.utils.Utils.randomString;

public class CourierGenerator {

    public static Courier randomCourier() {
        return new Courier()
                .withLogin(randomString(8))
                .withPassword(randomString(16))
                .withFirstName(randomString(10));
    }

    public static Courier courierWithoutLogin() {
        return new Courier()
                .withPassword(randomString(16))
                .withFirstName(randomString(10));
    }

    public static Courier courierWithoutPassword() {
        return new Courier()
                .withLogin(randomString(8))
                .withFirstName(randomString(10));
    }
 }
