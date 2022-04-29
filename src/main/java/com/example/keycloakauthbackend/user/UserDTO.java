package com.example.keycloakauthbackend.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class UserDTO {
    String id;
    String username;
    String password;

    @JsonCreator
    public UserDTO(@JsonProperty("id") String id,
                   @JsonProperty("username") String username,
                   @JsonProperty("password") String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
