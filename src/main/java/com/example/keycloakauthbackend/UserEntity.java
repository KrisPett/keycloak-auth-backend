package com.example.keycloakauthbackend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {
    @Id
    String id;
    @Column(name = "username")
    String username;
    @Column(name = "password")
    String password;
}

