package com.example.car_assistant.repository;

import com.example.car_assistant.model.Reminder;
import org.springframework.data.repository.CrudRepository;

public interface ReminderRepository extends CrudRepository<Reminder, Long> {

    Reminder findReminderByCarId(Long carId);

}
