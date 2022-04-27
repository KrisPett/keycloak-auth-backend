package com.example.keycloakauthbackend.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    private final Environment env;

    public SecurityConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity http) {
        return http
                .cors().and()
                .csrf().disable()
                .authorizeExchange().pathMatchers(HttpMethod.OPTIONS).permitAll().and()
                .authorizeExchange().pathMatchers(HttpMethod.GET, "/api/user/login/**").hasRole("employee").and()
                .authorizeExchange().anyExchange().hasAnyRole("user", "admin").and()
                .oauth2Client()
                .and()
                .oauth2ResourceServer(
                        oauth2ResourceServer -> {
                            oauth2ResourceServer.jwt(
                                    jwt -> jwt.jwtAuthenticationConverter(source ->
                                            Mono.just(Objects.requireNonNull(jwtAuthenticationConverter().convert(source))))
                            );
                        }).build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtConverter;
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return ReactiveJwtDecoders.fromOidcIssuerLocation(env.getRequiredProperty("spring.security.oauth2.client.provider.keycloak.issuer-uri"));
    }

}
