package com.booking;

import com.booking.car.Car;
import com.booking.car.CarService;
import com.booking.user.User;
import com.booking.user.UserService;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        CarService carService = new CarService();
        Car[] cars = carService.getAllCars();
        System.out.println("All cars :"+Arrays.toString(cars));
        Car bmw = carService.getCar("245");
        System.out.println("bmw :"+bmw);
        Car tesla = carService.getCar("001");
        System.out.println("is Tesla an electric car ? "+ tesla.isElectric());
        UserService userService = new UserService();
        User[] users = userService.getUsers();
        System.out.println("All users" + Arrays.toString(users));
    }
}