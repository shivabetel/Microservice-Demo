package com.oracle.microservices.common.web.exception;

public class OperationForbiddenException extends RuntimeException {

    public OperationForbiddenException(String message) {
        super(message);
    }
}
