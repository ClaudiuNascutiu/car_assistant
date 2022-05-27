package com.example.car_assistant.service;

import com.example.car_assistant.model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> getUsers();

    User updateUser(User user, Long id);

    void deleteUser(Long id);

    User findUserByEmail(String email);

    void sendMail(Long id);
}
