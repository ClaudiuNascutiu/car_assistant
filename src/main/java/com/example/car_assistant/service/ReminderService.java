package com.example.car_assistant.service;

import com.example.car_assistant.model.Reminder;

import java.io.IOException;

public interface ReminderService {

    void addReminder(Reminder reminder);

    void addReminder(Reminder reminder, Long carId);

    Reminder getReminderByCarId(Long carId);

    Reminder updateReminder(Reminder reminder, Long id);

    void deleteReminder(Long id);

    void expiredItp(Long userId, Long carId) throws IOException;

    void expiredInsurance(Long userId, Long carId) throws IOException;
}
