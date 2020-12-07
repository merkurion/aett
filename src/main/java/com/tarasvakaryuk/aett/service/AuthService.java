package com.tarasvakaryuk.aett.service;

import com.tarasvakaryuk.aett.entity.AuthRequest;
import com.tarasvakaryuk.aett.entity.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    @Value(value = "${ae.api.main.url}")
    private String apiUrl;

    @Value(value = "${ae.api.key}")
    private String apiKey;

    public AuthResponse getAuth() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AuthRequest> request = new HttpEntity<>(new AuthRequest(apiKey), httpHeaders);
        ResponseEntity<AuthResponse> response = restTemplate
                .exchange(apiUrl, HttpMethod.POST, request, AuthResponse.class);

        return response.getBody();
    }
}
