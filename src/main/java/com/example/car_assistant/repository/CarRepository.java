package com.example.car_assistant.repository;

import com.example.car_assistant.model.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findCarByUserId(Long userId);

}
