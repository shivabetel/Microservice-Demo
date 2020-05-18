package com.oracle.microservices.authenticationservice.web.controller;

import com.oracle.microservices.authenticationservice.web.model.ApplicationUser.ApplicationUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {



    public void authenticate(@RequestBody ApplicationUser user) {

    }
}
