package com.oracle.microservices.customerordermanagement;


import com.oracle.microservices.common.annotations.OrderManagementBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OrderManagementBootApplication
public class CustomerOrderManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerOrderManagementApplication.class);
    }
}
