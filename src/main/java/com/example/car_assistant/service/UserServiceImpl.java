package com.example.car_assistant.service;

import com.example.car_assistant.handler_exception.CustomException;
import com.example.car_assistant.mail_sender.EmailService;
import com.example.car_assistant.model.User;
import com.example.car_assistant.repository.UserRepository;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private EmailService emailService;


    public UserServiceImpl(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return ImmutableList.copyOf(userRepository.findAll());
    }

    @Override
    public User updateUser(User user, Long id) {
        userRepository.findById(id).
                orElseThrow(() -> new CustomException("User"));
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new CustomException("User"));
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void sendMail(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User"));
        emailService.sendMail(user.getEmail(),
                "Test", "Trimis din aplicatie");
    }
}
