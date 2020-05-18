package com.oracle.microservices.customermanagment.web.controller;

import com.oracle.microservices.common.entities.User;
import com.oracle.microservices.customermanagment.service.IUserService;
import com.oracle.microservices.customermanagment.web.model.UserDto;
import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.common.web.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController<User, Long, UserDto> {

    private IUserService userService;
    private IEntityDtoMapper<User, UserDto> userEntityDtoMapper;

    public UserController(IUserService userService, IEntityDtoMapper<User, UserDto>  userEntityDtoMapper) {
        this.userService = userService;
        this.userEntityDtoMapper = userEntityDtoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@RequestBody UserDto dto){
        return this.createOneInternal(dto);
    }

    @GetMapping
    public UserDto find(@RequestParam(name = "emailId") String emailId){
        return getService().findByEmailId(emailId)
                .map(getEntityToDtoMapper()::fromEntityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }



    @Override
    public IUserService getService() {
        return userService;
    }

    @Override
    public IEntityDtoMapper<User, UserDto> getEntityToDtoMapper() {
        return userEntityDtoMapper;
    }


}
