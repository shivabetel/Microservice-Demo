package com.oracle.microservices.authenticationservice.security.interfaces;


import com.oracle.microservices.authenticationservice.security.dto.AuthenticationResultDTO;

@FunctionalInterface
public interface ITokenValidation {

    AuthenticationResultDTO validateToken(String token);
}
