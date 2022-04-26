package com.example.keycloakauthbackend.model.DTOs;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Value;

@Value
public class UserDTO {
    String id;
    String username;
    String password;
}
