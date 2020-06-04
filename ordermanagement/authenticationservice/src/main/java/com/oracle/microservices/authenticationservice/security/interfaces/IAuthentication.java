package com.oracle.microservices.authenticationservice.security.interfaces;

import com.oracle.microservices.common.web.dtos.AuthenticationResponseDTO;
import org.springframework.security.core.Authentication;

public interface IAuthentication {

    AuthenticationResponseDTO authenticate(Authentication authentication);
}
