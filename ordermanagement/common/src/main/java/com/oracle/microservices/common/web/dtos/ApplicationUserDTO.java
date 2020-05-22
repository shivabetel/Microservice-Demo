package com.oracle.microservices.common.web.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationUserDTO {

    private String userName;
    private String password;
}
