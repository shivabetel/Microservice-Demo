package com.oracle.microservices.authenticationservice.web.controller;

import com.oracle.microservices.authenticationservice.security.dto.AuthenticationResultDTO;
import com.oracle.microservices.authenticationservice.security.interfaces.IAuthentication;
import com.oracle.microservices.authenticationservice.security.interfaces.ITokenValidation;
import com.oracle.microservices.common.web.dtos.ApplicationUserDTO;
import com.oracle.microservices.common.web.dtos.AuthenticationResponseDTO;
import com.oracle.microservices.common.web.exception.NotAuthorizedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {


   private IAuthentication authentication;

   private ITokenValidation jwtTokenValidation;


    public LoginController(IAuthentication authentication, ITokenValidation jwtTokenValidation) {
        this.authentication = authentication;
        this.jwtTokenValidation = jwtTokenValidation;
    }

    @PostMapping("/login")
    public AuthenticationResponseDTO authenticate(@RequestBody ApplicationUserDTO user) {
     return authentication.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
    }

    @GetMapping("/verify")
    public AuthenticationResultDTO verify(@RequestHeader(name = "Authorization") String token) {
        AuthenticationResultDTO authenticationResultDTO =  jwtTokenValidation.validateToken(token);
        if(authenticationResultDTO == null || authenticationResultDTO.getUser() == null){
            throw new NotAuthorizedException("Not Authorized");
        }

        return authenticationResultDTO;
    }
}
