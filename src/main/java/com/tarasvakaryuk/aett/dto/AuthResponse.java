package com.tarasvakaryuk.aett.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AuthResponse {

    private Boolean auth;
    private String token;

}
