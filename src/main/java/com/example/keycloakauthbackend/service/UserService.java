package com.example.keycloakauthbackend.service;

import com.example.keycloakauthbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    public String login(){
        return "login";
    }

}
