package com.example.car_assistant.service;

import com.example.car_assistant.handler_exception.CustomException;
import com.example.car_assistant.mail_sender.EmailService;
import com.example.car_assistant.model.Car;
import com.example.car_assistant.model.Reminder;
import com.example.car_assistant.model.User;
import com.example.car_assistant.repository.CarRepository;
import com.example.car_assistant.repository.ReminderRepository;
import com.example.car_assistant.repository.UserRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
public class ReminderServiceImpl implements ReminderService {

    private ReminderRepository reminderRepository;
    private CarRepository carRepository;
    private Car car;
    private EmailService emailService;

    private UserRepository userRepository;

    private User user;

    public ReminderServiceImpl(ReminderRepository reminderRepository, CarRepository carRepository, EmailService emailService, UserRepository userRepository ) {
        this.reminderRepository = reminderRepository;
        this.carRepository = carRepository;
        this.emailService = emailService;
        this.userRepository = userRepository;
    }

    @Override
    public void addReminder(Reminder reminder) {
        reminderRepository.save(reminder);
    }

    @Override
    public void addReminder(Reminder reminder, Long carId) {
        // Find car (already existent in DB).
        car = carRepository.findById(carId).orElseThrow(NoSuchElementException::new);

        // Link the two entities together
        // - for "no cascade", it's enough to link the remainder with a mock Car object with the correct carId.
        // - for "cascade all" (or cascade persist) on `Remainder.car` field, we need the real car object from the DB.
        reminder.setCar(car);
        car.setReminder(reminder);
        reminderRepository.save(reminder);
    }

    public Reminder getReminderByCarId(Long carId) {
        return reminderRepository.findReminderByCarId(carId);
    }

    @Override
    public Reminder updateReminder(Reminder reminder, Long id) {
        reminderRepository.findById(id)
                .orElseThrow(() -> new CustomException("Remainder"));

        reminder.setId(id);
        reminder.setCar(car);
        car.setReminder(reminder);
        return reminderRepository.save(reminder);
    }

    @Override
    public void deleteReminder(Long id) {
        reminderRepository.findById(id).orElseThrow(() -> new CustomException("Remainder"));
        reminderRepository.deleteById(id);
    }

    @Override
    public void expiredItp(Long userId, Long carId) throws IOException {
        user = userRepository.findById(userId).orElseThrow(() -> new CustomException("No user"));
        if (reminderRepository.findReminderByCarId(carId)
                .getItp()
                .isEqual(LocalDate.now().plusDays(10))) {
            emailService.sendMail(user.getEmail(), "Itp-ul tau urmeaza sa expire in 10 zile!",
                    "Salut" + " " + user.getLastName() + "," + "\n" + readFileForItpWillExpiredIn10Days());
        } else if (reminderRepository.findReminderByCarId(carId).getItp().isEqual(LocalDate.now())) {
            emailService.sendMail(user.getEmail(), "Itp-ul tau a expirat ",
                    "Salut" + " " + user.getLastName() + "," + "\n" + readFileForItp());
        }
    }

    @Override
    public void expiredInsurance(Long userId, Long carId) throws IOException {
        user = userRepository.findById(userId).orElseThrow(() -> new CustomException("No user"));
        if (reminderRepository.findReminderByCarId(carId)
                .getRca()
                .isEqual(LocalDate.now().plusDays(10))) {
            emailService.sendMail(user.getEmail(), "Asigurarea ta urmeaza sa expire in 10 zile!",
                    "Salut" + " " + user.getLastName() + "," + "\n" + readFileForInsuranceWillExpireIn10Days());
        } else if (reminderRepository.findReminderByCarId(carId)
                .getRca()
                .isEqual(LocalDate.now())) {
            emailService.sendMail(user.getEmail(), "Atentie! Asigurarea ta a expirat!",
                    "Salut" + " " + user.getLastName() + "," + "\n" + readFileForInsurance());

        }
    }

    public String readFileForItp() throws IOException {
        File resource = new ClassPathResource("itpTextForMail.txt").getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }

    public String readFileForItpWillExpiredIn10Days() throws IOException {
        File resource = new ClassPathResource("textForItpWillExpiredIn10Days.txttxt").getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }

    public String readFileForInsuranceWillExpireIn10Days() throws IOException {
        File resource = new ClassPathResource("textForInsuranceWillExpireIn10Days.txt").getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }

    public String readFileForInsurance() throws IOException {
        File resource = new ClassPathResource("textForInsuranceExpired.txt").getFile();
        return new String(Files.readAllBytes(resource.toPath()));
    }

}
