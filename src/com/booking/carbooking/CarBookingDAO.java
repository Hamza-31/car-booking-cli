package com.booking.carbooking;

import com.booking.car.CarDAO;

public class CarBookingDAO {
    private static final CarBooking[] carBookings;

    static {
        carBookings = new CarBooking[10];
    }
    public CarBooking[] getCarBookings(){
        return carBookings;
    }

    public void book(CarBooking carBooking){
        int nextFreeIndex = -1;
        for (int i=0;i<carBookings.length;i++) {
            if(carBookings[i] == null){
                nextFreeIndex = i;
            }
        }
        if(nextFreeIndex>-1){
            carBookings[nextFreeIndex] = carBooking;
            return;
        }
    }
}
