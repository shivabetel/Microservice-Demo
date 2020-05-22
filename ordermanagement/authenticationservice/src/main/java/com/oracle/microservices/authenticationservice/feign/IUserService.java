package com.oracle.microservices.authenticationservice.feign;

import com.oracle.microservices.common.web.dtos.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "customermanagement")
public interface IUserService {

    @GetMapping("/api/user")
    UserDTO findByEmailId(@RequestParam(name = "emailId") String emailId);
}
