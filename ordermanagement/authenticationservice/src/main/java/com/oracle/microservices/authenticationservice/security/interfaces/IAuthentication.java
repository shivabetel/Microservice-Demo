package com.oracle.microservices.authenticationservice.security.interfaces;

import org.springframework.security.core.Authentication;

public interface IAuthentication {

    String authenticate(Authentication authentication);
}
