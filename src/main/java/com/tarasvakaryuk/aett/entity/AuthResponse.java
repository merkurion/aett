package com.tarasvakaryuk.aett.entity;

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
