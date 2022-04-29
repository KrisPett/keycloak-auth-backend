
package com.example.keycloakauthbackend.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Data
public class KeyCloakToken {
    String accessToken;
    int expiresIn;
    int refreshExpiresIn;
    String refreshToken;
    String tokenType;
    int notBeforePolicy;
    String sessionState;
    String scope;

    @JsonCreator
    public KeyCloakToken(@JsonProperty("access_token") String accessToken,
                         @JsonProperty("expires_in") int expiresIn,
                         @JsonProperty("refresh_expires_in") int refreshExpiresIn,
                         @JsonProperty("refresh_token") String refreshToken,
                         @JsonProperty("token_type") String tokenType,
                         @JsonProperty("not-before-policy") int notBeforePolicy,
                         @JsonProperty("session_state") String sessionState,
                         @JsonProperty("scope") String scope) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshExpiresIn = refreshExpiresIn;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.notBeforePolicy = notBeforePolicy;
        this.sessionState = sessionState;
        this.scope = scope;
    }

    public static Mono<KeyCloakToken> acquire(String keyCloakBaseUrl, String realm, String clientId, String username, String password) {
        WebClient webClient = WebClient.builder()
                .baseUrl(keyCloakBaseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();
        return webClient.post()
                .uri("realms/" + realm + "/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "password")
                        .with("client_id", clientId)
                        .with("username", username)
                        .with("password", password)
                        .with("access_token", ""))
                .retrieve()
                .bodyToFlux(KeyCloakToken.class)
                .onErrorMap(e -> new Exception("Failed to acquire token", e))
                .last();
    }
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void main(String[] args) {
        KeyCloakToken token = acquire(
                "http://localhost:8080/",
                "chainqt3",
                "chainqt3-keycloak",
                "u",
                "u")
                .block();

        System.out.println(token);
        System.out.println("KeyCloakToken.main ************************************");
        System.out.println(ANSI_GREEN + token.accessToken + ANSI_RESET);
        System.out.println("KeyCloakToken.main ************************************");
    }
}


