package edu.practikum.sprint7.models;

public class CourierCreds {

    private String login;
    private String password;

    public CourierCreds(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static CourierCreds fromCourier(Courier courier) {
        return new CourierCreds(courier.getLogin(), courier.getPassword());
    }

    public static CourierCreds customLoginCourier(String login, Courier courier) {
        return new CourierCreds(login, courier.getPassword());
    }

    public static CourierCreds customPasswordCourier(Courier courier, String password) {
        return new CourierCreds(courier.getLogin(), password);
    }

    @Override
    public String toString() {
        return "CourierCred{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
