package com.oracle.microservices.authenticationservice.security.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.oracle.microservices.authenticationservice.security.User;
import com.oracle.microservices.authenticationservice.security.interfaces.IAuthentication;
import com.oracle.microservices.common.web.dtos.AddressDTO;
import com.oracle.microservices.common.web.dtos.AuthenticationResponseDTO;
import com.oracle.microservices.common.web.dtos.UserDTO;
import com.oracle.microservices.common.web.utils.TokenHelper;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Primary
public class JWTAuthentication implements IAuthentication {


    private AuthenticationManager authenticationManager;

    public JWTAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponseDTO authenticate(Authentication authentication) {
        Authentication authResult = authenticationManager.authenticate(authentication);
        Map<String, String> claims = new HashMap<>();
        claims.put("customerId", ((User) authResult.getPrincipal()).getCustomerId());
        claims.put("firstName", ((User) authResult.getPrincipal()).getFirstName());
        claims.put("lastName", ((User) authResult.getPrincipal()).getLastName());
        AuthenticationResponseDTO responseDTO = new AuthenticationResponseDTO();
        responseDTO.setAuthToken(TokenHelper.createToken(((User) authResult.getPrincipal()).getUsername(), "user", claims));
        responseDTO.setPayload(new UserDTO(((User) authResult.getPrincipal()).getCustomerId(),
                ((User) authResult.getPrincipal()).getEmailId(),
                null,
                ((User) authResult.getPrincipal()).getFirstName(),
                ((User) authResult.getPrincipal()).getLastName(),
                ((User) authResult.getPrincipal()).getShippingAddress().stream().collect(Collectors.toList()))
        );
        return responseDTO;
//        return JWT.create()
//                .withSubject(((User)authResult.getPrincipal()).getUsername())
//                .withIssuer("oracle")
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
//                .withClaim("user", claims)
//                .sign(Algorithm.HMAC512(JWTAuthentication.privateKey.getBytes()));
    }
}
