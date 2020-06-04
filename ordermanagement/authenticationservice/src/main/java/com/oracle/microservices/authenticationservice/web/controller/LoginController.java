package com.oracle.microservices.authenticationservice.web.controller;

import com.oracle.microservices.authenticationservice.security.interfaces.IAuthentication;
import com.oracle.microservices.common.web.dtos.ApplicationUserDTO;
import com.oracle.microservices.common.web.dtos.AuthenticationResponseDTO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {


   private IAuthentication authentication;


    public LoginController(IAuthentication authentication) {
        this.authentication = authentication;
    }

    @PostMapping
    public AuthenticationResponseDTO authenticate(@RequestBody ApplicationUserDTO user) {
     return authentication.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
    }
}
