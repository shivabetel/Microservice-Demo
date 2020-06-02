package com.oracle.microservices.apigateway.security.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.oracle.microservices.apigateway.security.interfaces.ITokenValidation;
import org.springframework.stereotype.Component;


@Component
public class JWTTokenValidation implements ITokenValidation {

    @Override
    public String validateToken(String token) {
        return JWT.require(Algorithm.HMAC512("abcdefghijklmn".getBytes()))
                  .build()
                  .verify(token)
                  .getSubject();
    }

}
