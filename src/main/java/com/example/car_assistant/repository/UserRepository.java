package com.example.car_assistant.repository;

import com.example.car_assistant.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);

}
