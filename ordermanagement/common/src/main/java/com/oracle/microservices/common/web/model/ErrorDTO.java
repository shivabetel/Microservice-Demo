package com.oracle.microservices.common.web.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDTO {

    private String responseCode;
    private String responseMessage;
}
