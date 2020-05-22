package com.oracle.microservices.apigateway.security;

import com.oracle.microservices.apigateway.security.interfaces.ITokenValidation;
import com.oracle.microservices.apigateway.web.dto.AuthenticationResultDTO;
import org.springframework.stereotype.Component;

@Component
public class TokenValidationManager {


    private ITokenValidation tokenValidation;

    public TokenValidationManager(ITokenValidation tokenValidation) {
        this.tokenValidation = tokenValidation;
    }

    public AuthenticationResultDTO validateToken(String token){
        AuthenticationResultDTO authenticationResultDTO = new AuthenticationResultDTO();
        try{
            String user = null;
            if(token != null && token.startsWith("Bearer")){
                authenticationResultDTO.setUser(tokenValidation.validateToken(token.replace("Bearer ", "")));
            }

        }catch (Exception e) {
            authenticationResultDTO.setUser(null);
        }

        return authenticationResultDTO;


    }
}
