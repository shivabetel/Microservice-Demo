package com.oracle.microservices.authenticationservice.security.impl;


import com.oracle.microservices.authenticationservice.security.dto.AuthenticationResultDTO;
import com.oracle.microservices.authenticationservice.security.interfaces.ITokenValidation;
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
