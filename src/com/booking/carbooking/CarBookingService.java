package com.booking.carbooking;

import com.booking.car.Car;
import com.booking.car.CarService;
import com.booking.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarBookingService {
    private final CarBookingDAO carBookingDAO;
    private final CarService carService;

    public CarBookingService(CarBookingDAO carBookingDAO, CarService carService) {
        this.carBookingDAO = carBookingDAO;
        this.carService = carService;
    }

    private Car[] getCars(Car[] cars){
        if(cars.length == 0){
            return new Car[0];
        }
        CarBooking[] carBookings = carBookingDAO.getCarBookings();
        if(carBookings.length == 0){
            return cars;
        }
        int availableCarsCount = 0;
        for (Car car : cars) {
            boolean booked = false;
            for (CarBooking carBooking : carBookings) {
                if(carBooking == null || !carBooking.getCar().equals(car)){
                    continue;
                }
                booked=true;
            }
            if (!booked){
                ++availableCarsCount;
            }
        }
        Car[] availableCars = new Car[availableCarsCount];
        int index = 0;
        for (Car car : cars) {
            boolean booked = false;
            for (CarBooking carBooking : carBookings) {
                if(carBooking == null || !carBooking.getCar().equals(car)){
                    continue;
                }
                booked = true;
            }
            if(!booked){
                availableCars[index++] = car;
            }

        }
        return availableCars;
    }

    public UUID bookCar(User user, String regNumber){
    Car[] availableCars = getAvailableCars();
    if(availableCars.length == 0){
        throw new IllegalStateException("No car available for renting.");
    }
        for (Car availableCar : availableCars) {
            if(availableCar.getRegNumber().equals(regNumber)){
                Car car = carService.getCar(regNumber);
                UUID bookingId = UUID.randomUUID();
                carBookingDAO.book(
                        new CarBooking(bookingId,user,car, LocalDateTime.now())
                );
                return bookingId;
            }
        }
        throw new IllegalStateException(String.format("Car with registration number %s is not found", regNumber));
    }
    public CarBooking[] getBookings(){
        CarBooking[] carBookings = carBookingDAO.getCarBookings();
        int numberOfBookings = 0;
        for (CarBooking carBooking : carBookings) {
            if(carBooking != null){
                ++numberOfBookings;
            }
        }
        if(numberOfBookings==0){
            return new CarBooking[0];
        }
        CarBooking[] bookings = new CarBooking[numberOfBookings];
        int index = 0;
        for (CarBooking carBooking : carBookings) {
            if(carBooking != null){
                bookings[index++] = carBooking;
            }

        }
        return bookings;
    }
    public Car[] getUserBookedCars(UUID userId){
        CarBooking[] carBookings = carBookingDAO.getCarBookings();
        int numberOfBookingsForUser = 0;
        for (CarBooking carBooking : carBookings) {
            if(carBooking != null && carBooking.getUser().getId().equals(userId)){
                ++numberOfBookingsForUser;
            }
        }
        if(numberOfBookingsForUser == 0){
            return new Car[0];
        }
        Car[] usersCars = new Car[numberOfBookingsForUser];
        int index = 0;
        for (CarBooking carBooking : carBookings) {
            if(carBooking != null && carBooking.getUser().getId().equals(userId)){
                usersCars[index++] = carBooking.getCar();
            }
        }
        return usersCars;
    }
    public Car[] getAvailableCars(){
        return getCars(carService.getAllCars());
    }
    public Car[] getAvailableElectricCars(){
        return getCars(carService.getAllElectricCars());
    }
}
