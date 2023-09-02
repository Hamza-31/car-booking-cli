package com.booking.car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Car> electricCars = getAllCars()
                .stream()
                .filter(Car::isElectric)
                .collect(Collectors.toList());

        return electricCars;
    }
}
