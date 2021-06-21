package com.oracle.microservices.common.spring;

import com.oracle.microservices.common.interfaces.ISecuredOperation;
import com.oracle.microservices.common.service.SecuredOperationImpl;
import com.oracle.microservices.common.web.FeignExceptionHandler;
import com.oracle.microservices.common.web.RestResponseEntityExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:persistence-mysql-local.properties")
@Import(RestResponseEntityExceptionHandler.class)
@Configuration
public class CommonConfig {

    @Bean
    public FeignExceptionHandler feignErrorDecoder(){
        return new FeignExceptionHandler();
    }

    @Bean
    public ISecuredOperation securedOperation(){
        return new SecuredOperationImpl();
    }
}
