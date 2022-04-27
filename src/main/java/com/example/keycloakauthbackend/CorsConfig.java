package com.example.keycloakauthbackend;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.CacheControl;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
@ConfigurationProperties(prefix = "keycloak-auth.cors")
@Getter
@Setter
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig implements WebFluxConfigurer {

    private static final String[] ALLOWED_HEADERS = {"Authorization", "content-type"};
    private static final String[] ALLOWED_METHODS = {"GET", "PUT", "POST", "DELETE", "OPTIONS"};
    private String allowedOrigin = "*";
    private int maxAge = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(allowedOrigin.split(","))
                .allowedMethods(ALLOWED_METHODS)
                .allowedHeaders(ALLOWED_HEADERS)
                .maxAge(maxAge);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*/**").setCacheControl(CacheControl.noCache());
    }
}
