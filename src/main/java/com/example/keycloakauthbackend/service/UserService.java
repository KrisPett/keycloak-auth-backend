package com.example.keycloakauthbackend.service;

import com.example.keycloakauthbackend.model.UserEntity;
import com.example.keycloakauthbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(){
        return "login";
    }

    public UserEntity createAccount(String username, String password) {
        UserEntity userEntity = new UserEntity(UUID.randomUUID().toString(), username, password);
        userRepository.save(userEntity);
        return userEntity;
    }
}
