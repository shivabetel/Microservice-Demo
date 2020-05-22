package com.oracle.microservices.authenticationservice;

import com.oracle.microservices.common.annotations.OrderManagementBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OrderManagementBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class AuthenticationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class,args);
    }
}
