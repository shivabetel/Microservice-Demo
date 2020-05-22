package com.oracle.microservices.common.web;

import com.oracle.microservices.common.web.exception.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.web.client.ResourceAccessException;

public class FeignExceptionHandler implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 404: {
                return new ResourceNotFoundException(response.reason());
            }
            default:{
                return new Exception(response.reason());
            }
        }
    }
}
