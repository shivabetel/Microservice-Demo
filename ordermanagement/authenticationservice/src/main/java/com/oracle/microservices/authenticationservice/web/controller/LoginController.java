package com.oracle.microservices.authenticationservice.web.controller;

import com.oracle.microservices.common.web.dtos.ApplicationUserDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {



    public void authenticate(@RequestBody ApplicationUserDTO user) {

    }
}
