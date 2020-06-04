package com.oracle.microservices.customermanagment.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.service.interfaces.IService;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.common.web.dtos.UserDTO;
import com.oracle.microservices.customermanagment.persistence.model.User;
import com.oracle.microservices.customermanagment.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/registration")
public class UserRegistrationController extends AbstractController<User, Long, UserDTO> {

    private IUserService userService;
    private IEntityDtoMapper<User, UserDTO> userEntityDtoMapper;

    public UserRegistrationController(IUserService userService, IEntityDtoMapper<User, UserDTO>  userEntityDtoMapper) {
        this.userService = userService;
        this.userEntityDtoMapper = userEntityDtoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO dto){
        return this.createOneInternal(dto);
    }

    @GetMapping("/checkAvailability")
    public String checkAvailability(@RequestParam(name = "emailId") String emailId){
       return getService().findByEmailId(emailId).isPresent() ? "{\"available\": false }" : "{\"available\": true }";
    }


    @Override
    public IUserService getService() {
        return userService;
    }

    @Override
    public IEntityDtoMapper<User, UserDTO> getEntityToDtoMapper() {
        return userEntityDtoMapper;
    }
}
