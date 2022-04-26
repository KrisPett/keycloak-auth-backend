package com.example.keycloakauthbackend.rest;

import com.example.keycloakauthbackend.model.DTOs.UserDTO;
import com.example.keycloakauthbackend.model.UserEntity;
import com.example.keycloakauthbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("create-account")
    public UserDTO createAccount(@RequestParam String username, @RequestParam String password) {
        UserEntity userEntity = userService.createAccount(username, password);
        return userDTO(userEntity);
    }

    @GetMapping("login")
    public String login(){
       return userService.login();
    }

    @GetMapping("logout")
    public String logout(){
        return "logout";
    }


    public UserDTO userDTO(UserEntity userEntity){
        return new UserDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
    }
}
