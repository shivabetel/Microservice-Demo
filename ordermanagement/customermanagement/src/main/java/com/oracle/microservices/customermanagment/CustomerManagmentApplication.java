package com.oracle.microservices.customermanagment;

import com.oracle.microservices.common.spring.CommonConfig;
import com.oracle.microservices.common.web.RestResponseEntityExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableDiscoveryClient
@PropertySource("classpath:persistence-local.properties")
@Import({RestResponseEntityExceptionHandler.class, CommonConfig.class})
public class CustomerManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerManagmentApplication.class);
    }
}
