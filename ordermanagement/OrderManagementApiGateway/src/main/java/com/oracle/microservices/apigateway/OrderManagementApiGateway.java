package com.oracle.microservices.apigateway;


import com.oracle.microservices.apigateway.web.filter.ZuulPreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class OrderManagementApiGateway {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementApiGateway.class, args);
    }

}
