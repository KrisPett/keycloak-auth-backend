package com.example.keycloakauthbackend.dashboard;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/dashboard")
@AllArgsConstructor
public class DashboardViewController {

    @GetMapping("dashboard")
    public DashboardViewDTO dashboard() {
        return new DashboardViewDTO(UUID.randomUUID().toString(), "name");
    }
}
