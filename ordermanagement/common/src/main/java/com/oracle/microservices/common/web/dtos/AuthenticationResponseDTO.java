package com.oracle.microservices.common.web.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponseDTO {

    private String authToken;
}
