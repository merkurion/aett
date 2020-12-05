package com.tarasvakaryuk.aett.configuration;

import com.tarasvakaryuk.aett.dto.AuthResponse;
import com.tarasvakaryuk.aett.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class AuthConfig {

    final AuthService authService;

    @Bean
    public AuthResponse getAuth() {
        return authService.getAuth();
    }
}
