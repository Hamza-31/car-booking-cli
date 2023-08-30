package com.booking.car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarService {
    private final CarDAO carDAO;

    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public List<Car> getAllCars(){
        return carDAO.getCars();
    }
    public Car getCar(String regNumber){
        for (Car car : getAllCars()) {
            if(regNumber.equals(car.getRegNumber())){
                return car;
            }
        }
        throw new IllegalStateException((String.format("Car with registration number %s is not found", regNumber)));
    }
    public List<Car> getAllElectricCars() {
        int electricCarsCount = 0;

        List<Car> cars = getAllCars();

        if (cars.isEmpty()) {
            return Collections.emptyList();
        }

        for (Car car : cars) {
            if (car.isElectric()) {
                electricCarsCount++;
            }
        }

        if (electricCarsCount == 0) {
            return Collections.emptyList();
        }

        List<Car> electricCars = new ArrayList<>();

        int index = 0;


        for (Car car : cars) {
            if (car.isElectric()) {
                electricCars.add(car);
            }
        }

        return electricCars;
    }
}
