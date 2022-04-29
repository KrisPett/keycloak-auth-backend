package com.example.keycloakauthbackend.user;

import com.example.keycloakauthbackend.UserDTO;
import com.example.keycloakauthbackend.util.KeyCloakToken;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserController {
    @LocalServerPort
    int port;

    @Test
    void test_controller_create_account() {
        KeyCloakToken token = KeyCloakToken.acquire(
                        "http://localhost:8080/",
                        "chainqt3",
                        "chainqt3-keycloak",
                        "u",
                        "u")
                .block();

        UserDTO userDTO = WebClient
                .create(baseURL("/api/user/create-account?username=u&password=u"))
                .get()
                .header("Authorization", "Bearer " + token.getAccessToken())
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();

        assertEquals("u", userDTO.getUsername());
        assertEquals("u", userDTO.getPassword());
    }

    private String baseURL(String url) {
        return "http://localhost:" + port + url;
    }

}
