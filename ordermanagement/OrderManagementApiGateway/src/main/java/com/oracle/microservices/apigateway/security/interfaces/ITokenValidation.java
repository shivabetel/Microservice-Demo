package com.oracle.microservices.apigateway.security.interfaces;


import com.oracle.microservices.apigateway.web.dto.AuthenticationResultDTO;

@FunctionalInterface
public interface ITokenValidation {

    AuthenticationResultDTO validateToken(String token);
}
