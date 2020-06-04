package com.oracle.microservices.authenticationservice.security;

import com.oracle.microservices.authenticationservice.feign.IUserService;
import com.oracle.microservices.common.web.exception.ResourceNotFoundException;
import com.oracle.microservices.common.web.dtos.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private IUserService userService;

    public MyUserDetailsService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        try{
            UserDTO user =  userService.findByEmailId(userId);
            return new User(user.getEmailId(), user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getCustomerId(),
                    user.getEmailId(),
                    user.getShippingAddress());

        }catch (ResourceNotFoundException ex){
            throw new UsernameNotFoundException("User with email id "+userId+" doesn't exist");
        }


    }
}
