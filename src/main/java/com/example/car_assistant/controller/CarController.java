package com.example.car_assistant.controller;

import com.example.car_assistant.model.Car;
import com.example.car_assistant.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mycarapp/car")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getCars();
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Car> addCar(@RequestBody Car car, @PathVariable Long userId) {
        return ResponseEntity.ok(carService.addCar(car, userId));
    }

    @PutMapping("{id}")
    public Car updateCar(@RequestBody Car car, @PathVariable Long id) {
        return carService.updateCar(car, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }

    @GetMapping("/findCar/{userId}")
    public List<Car> findCarsByUserId(@PathVariable Long userId) {
        return carService.findCarByUserId(userId);
    }


}
