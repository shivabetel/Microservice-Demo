package com.oracle.microservices.apigateway.security.interfaces;


@FunctionalInterface
public interface ITokenValidation {

    String validateToken(String token);
}
