package com.example.car_assistant.service;

import com.example.car_assistant.handler_exception.CustomException;
import com.example.car_assistant.model.Car;
import com.example.car_assistant.model.User;
import com.example.car_assistant.repository.CarRepository;
import com.example.car_assistant.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    private UserRepository userRepository;

    private User user;

    public CarServiceImpl(CarRepository carRepository, UserRepository userRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Car addCar(Car car, Long userId) {

        user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);

        car.setUser(user);
        return carRepository.save(car);
    }

    @Override
    public List<Car> getCars() {
        return ImmutableList.copyOf(carRepository.findAll());
    }

    @Override
    public Car updateCar(Car car, Long id) {
        carRepository.findById(id).
                orElseThrow(() -> new CustomException("Car"));
        car.setId(id);
        car.setUser(user);
        return carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.findById(id).orElseThrow(() -> new CustomException("Car"));
        carRepository.deleteById(id);
    }

    @Override
    public List<Car> findCarByUserId(Long userId) {
        return ImmutableList.copyOf(carRepository.findCarByUserId(userId));
    }

}
