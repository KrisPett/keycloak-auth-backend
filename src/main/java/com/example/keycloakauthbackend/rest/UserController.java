package com.example.keycloakauthbackend.rest;

import com.example.keycloakauthbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@AllArgsConstructor
public class UserController {
    UserService userService;

    /*@RequestMapping(method = RequestMethod.GET, value = "/api/javainuse")*/
    @GetMapping("login")
    public String login(){
       return userService.login();
    }

    @GetMapping("logout")
    public String logout(){
        return "logout";
    }

}
