package com.example.car_assistant.service;

import com.example.car_assistant.model.Car;

import java.util.List;

public interface CarService {

    Car addCar(Car car, Long userId);

    List<Car> getCars();

    Car updateCar(Car car, Long id);

    void deleteCar(Long id);

    List<Car> findCarByUserId(Long userId);

}
