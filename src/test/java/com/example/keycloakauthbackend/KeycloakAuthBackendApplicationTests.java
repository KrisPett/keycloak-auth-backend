package com.example.keycloakauthbackend;

import com.example.keycloakauthbackend.user.UserDTO;
import com.example.keycloakauthbackend.util.KeyCloakToken;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class KeycloakAuthBackendApplicationTests {
    @LocalServerPort
    int port;

    @Test
    void contextLoads() {
        KeyCloakToken token = KeyCloakToken.acquire(
                        "http://localhost:8080/",
                        "chainqt3",
                        "chainqt3-keycloak",
                        "u",
                        "u")
                .block();

        UserDTO userDTO = WebClient
                .create(getUrl("/api/user/create-account?username=u&password=u"))
                .get()
                .header("Authorization", "Bearer " + token.getAccessToken())
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();

        assertEquals("u", userDTO.getUsername());
        assertEquals("u", userDTO.getPassword());
    }

    private String getUrl(String url) {
        return "http://localhost:" + port + url;
    }

}
