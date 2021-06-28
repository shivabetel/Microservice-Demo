package com.oracle.microservices.common.web;


import com.auth0.jwt.exceptions.TokenExpiredException;
import com.oracle.microservices.common.web.exception.NotAuthorizedException;
import com.oracle.microservices.common.web.exception.OperationForbiddenException;
import com.oracle.microservices.common.web.exception.ResourceNotFoundException;
import com.oracle.microservices.common.web.dtos.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity handleResourceNotFound(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setResponseCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        errorDTO.setResponseMessage(ex.getMessage());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleGenericException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setResponseCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        errorDTO.setResponseMessage(ex.getMessage());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {TokenExpiredException.class})
    public ResponseEntity handleTokenExpired(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setResponseCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        errorDTO.setResponseMessage("Token is expired");
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(value = {OperationForbiddenException.class})
    public ResponseEntity handleOperationForbiddenException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setResponseCode(String.valueOf(HttpStatus.FORBIDDEN.value()));
        errorDTO.setResponseMessage(ex.getMessage());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler(value = {NotAuthorizedException.class})
    public ResponseEntity handleNotAuthorizedException(Exception ex, WebRequest request) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setResponseCode(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
        errorDTO.setResponseMessage(ex.getMessage());
        return handleExceptionInternal(ex, errorDTO, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }


}
