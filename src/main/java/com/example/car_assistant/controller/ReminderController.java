package com.example.car_assistant.controller;


import com.example.car_assistant.model.Reminder;
import com.example.car_assistant.service.ReminderService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/mycarapp/reminder")
public class ReminderController {

    private ReminderService reminderService;

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }


    @PostMapping
    public void addRemainder(@RequestBody Reminder reminder) {
        reminderService.addReminder(reminder);
    }

    // or Add an endpoint in the Car controller with the path "/mycarapp/car/{carId}/reminder"
    @PostMapping("/car/{carId}")
    public void addRemainderToCar(@RequestBody Reminder reminder, @PathVariable Long carId) {
        reminderService.addReminder(reminder, carId);
    }

    @GetMapping("/car/{carId}")
    public Reminder getReminderByCarId(@PathVariable Long carId) {
        return reminderService.getReminderByCarId(carId);
    }

    @PutMapping("/{id}")
    public Reminder updateRemainder(@RequestBody Reminder reminder, @PathVariable Long id) {
        return reminderService.updateReminder(reminder, id);
    }

    @DeleteMapping("/{id}")
    public void deleteRemainder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
    }

    @GetMapping("/itp/{userId}/{carId}")
    public void expiredItp(@PathVariable Long userId, @PathVariable Long carId) throws IOException {
        reminderService.expiredItp(userId, carId);
    }

    @GetMapping("/insurance/{userId}/{carId}")
    public void expiredInsurance(@PathVariable Long userId, @PathVariable Long carId) throws IOException {
        reminderService.expiredInsurance(userId, carId);
    }

}
