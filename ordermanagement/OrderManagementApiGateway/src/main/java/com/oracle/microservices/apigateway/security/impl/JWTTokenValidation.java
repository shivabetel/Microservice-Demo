package com.oracle.microservices.apigateway.security.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.oracle.microservices.apigateway.security.interfaces.ITokenValidation;
import com.oracle.microservices.apigateway.web.dto.AuthenticationResultDTO;
import com.oracle.microservices.common.web.dtos.AuthenticationResponseDTO;
import com.oracle.microservices.common.web.utils.TokenHelper;
import org.springframework.stereotype.Component;


@Component
public class JWTTokenValidation implements ITokenValidation {

    @Override
    public AuthenticationResultDTO validateToken(String token) {
        AuthenticationResultDTO authenticationResultDTO = new AuthenticationResultDTO();
        try{
            if(token != null && token.startsWith("Bearer")){
                authenticationResultDTO.setUser(TokenHelper.verifyToken(token.replace("Bearer ", "")));
            }

        }catch (Exception e) {
            authenticationResultDTO.setUser(null);
        }

        return authenticationResultDTO;
    }

}
