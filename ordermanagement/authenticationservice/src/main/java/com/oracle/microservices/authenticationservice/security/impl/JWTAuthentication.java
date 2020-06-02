package com.oracle.microservices.authenticationservice.security.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.oracle.microservices.authenticationservice.security.User;
import com.oracle.microservices.authenticationservice.security.interfaces.IAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTAuthentication implements IAuthentication {

    private static String privateKey = "abcdefghijklmn";

    private AuthenticationManager authenticationManager;

    public JWTAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String authenticate(Authentication authentication) {
       Authentication authResult =  authenticationManager.authenticate(authentication);
        Map<String, String> claims = new HashMap<>();
        claims.put("customerId", ((User)authResult.getPrincipal()).getCustomerId());
        claims.put("firstName", ((User)authResult.getPrincipal()).getFirstName());
        claims.put("lastName", ((User)authResult.getPrincipal()).getLastName());
        return JWT.create()
                .withSubject(((User)authResult.getPrincipal()).getUsername())
                .withIssuer("oracle")
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
                .withClaim("user", claims)
                .sign(Algorithm.HMAC512(JWTAuthentication.privateKey.getBytes()));
    }
}
