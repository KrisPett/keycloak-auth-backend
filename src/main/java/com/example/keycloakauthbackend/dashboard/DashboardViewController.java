package com.example.keycloakauthbackend.dashboard;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("api/dashboard")
@AllArgsConstructor
public class DashboardViewController {

    @GetMapping("dashboard")
    public Mono<DashboardViewDTO> dashboard() {
        return Mono.just(new DashboardViewDTO(UUID.randomUUID().toString(), "name"));
    }
}
