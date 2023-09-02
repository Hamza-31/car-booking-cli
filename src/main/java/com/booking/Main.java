package com.booking;


import com.booking.car.Car;
import com.booking.car.CarDAO;
import com.booking.car.CarService;
import com.booking.carbooking.CarBooking;
import com.booking.carbooking.CarBookingDAO;
import com.booking.carbooking.CarBookingService;
import com.booking.user.User;
import com.booking.user.UserService;

import java.io.File;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        CarBookingDAO carBookingDAO= new CarBookingDAO();

        CarDAO carDAO = new CarDAO();
        CarService carService = new CarService(carDAO);

        CarBookingService carBookingService = new CarBookingService(carBookingDAO,carService);

        Scanner scanner = new Scanner(System.in);

        boolean keepLooping = true;
        while (keepLooping) {
            try {
                displayMenu();
                String choice = scanner.nextLine();
                switch (Integer.parseInt(choice)) {
                    case 1 -> bookCar(userService, carBookingService, scanner);
                    case 2 -> displayAllUserBookedCars(userService, carBookingService, scanner);
                    case 3 -> displayAllBookings(carBookingService);
                    case 4 -> displayAvailableCars(carBookingService, false);
                    case 5 -> displayAvailableCars(carBookingService, true);
                    case 6 -> displayAllUsers(userService);
                    case 7 -> keepLooping = false;
                    default -> System.out.println(choice + " not a valid option ❌");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }




    private static void displayAllBookings(CarBookingService carBookingService){
        List<CarBooking> bookings = carBookingService.getBookings();
        if(bookings.isEmpty()){
            System.out.println("No bookings available !");
        return;
        }
        for (CarBooking booking : bookings) {
            System.out.println("booking = "+booking);
        }
    }
    private static void displayAllUsers(UserService userService){
       List<User> users = userService.getUsers();
        if(users.isEmpty()){
            System.out.println("No user in the system !");
            return;
        }
        for (User user : users) {
            System.out.println(user);
        }
    }
    private static void displayAvailableCars(CarBookingService carBookingService, boolean isElectric) {
        List<Car> availableCars = isElectric ? carBookingService.getAvailableElectricCars() : carBookingService.getAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("❌ No cars available for renting");
            return;
        }
        for (Car availableCar : availableCars) {
            System.out.println(availableCar);
        }
    }

    private static void displayAllUserBookedCars(UserService userService,
                                                 CarBookingService carBookingService,
                                                 Scanner scanner) {
        displayAllUsers(userService);

        System.out.println("-select user id");
        String userId = scanner.nextLine();

        User user = userService.getUserById(UUID.fromString(userId));
        if (user == null) {
            System.out.println("❌ No user found with id " + userId);
            return;
        }

        List<Car> userBookedCars = carBookingService.getUserBookedCars(user.getId());
        if (userBookedCars.isEmpty()) {
            System.out.printf("❌ user %s has no cars booked", user);
            return;
        }
        for (Car userBookedCar : userBookedCars) {
            System.out.println(userBookedCar);
        }
    }
    private static void bookCar(UserService userService, CarBookingService carBookingService, Scanner scanner) {
        displayAvailableCars(carBookingService, false);

        System.out.println("-select car reg number");
        String regNumber = scanner.nextLine();

        displayAllUsers(userService);

        System.out.println("-select user id");
        String userId = scanner.nextLine();

        try {
            User user = userService.getUserById(UUID.fromString(userId));
            if (user == null) {
                System.out.println("❌ No user found with id " + userId);
            } else {
                UUID bookingId = carBookingService.bookCar(user, regNumber);
                String confirmationMessage = """
                        Successfully booked car with reg number %s for user %s
                        Booking ref: %s
                        """.formatted(regNumber, user, bookingId);
                System.out.println(confirmationMessage);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private static void displayMenu(){
        System.out.println("""
                \n
                1 - Book Car
                2 - View All User Booked Cars
                3 - View All Bookings
                4 - View Available Cars
                5 - View Available Electric Cars
                6 - View all users
                7 - Exit
                """);
    }


}