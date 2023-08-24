package com.booking.carbooking;

import com.booking.car.Car;
import com.booking.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarBooking {
    private UUID bookingId;
    private User user;
    private Car car;
    private LocalDateTime bookingTime;
    private boolean isCanceled;

    public CarBooking(UUID bookingId,User user, Car car, LocalDateTime bookingTime, boolean isCanceled) {
        this.bookingId = bookingId;
        this.user = user;
        this.car = car;
        this.bookingTime = bookingTime;
        this.isCanceled = isCanceled;
    }
    public CarBooking(UUID bookingId, User user, Car car, LocalDateTime bookingTime){
        this(bookingId,user,car,bookingTime,false);
    }
}
