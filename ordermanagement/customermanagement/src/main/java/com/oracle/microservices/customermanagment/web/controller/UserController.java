package com.oracle.microservices.customermanagment.web.controller;

import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.common.web.dtos.UserDTO;
import com.oracle.microservices.common.web.exception.ResourceNotFoundException;
import com.oracle.microservices.customermanagment.persistence.model.User;
import com.oracle.microservices.customermanagment.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController<User, Long, UserDTO> {

    private IUserService userService;
    private IEntityDtoMapper<User, UserDTO> userEntityDtoMapper;

    public UserController(IUserService userService, IEntityDtoMapper<User, UserDTO>  userEntityDtoMapper) {
        this.userService = userService;
        this.userEntityDtoMapper = userEntityDtoMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody UserDTO dto){
        return this.createOneInternal(dto);
    }

    @GetMapping
    public UserDTO find(@RequestParam(name = "emailId") String emailId){
        return getService().findByEmailId(emailId)
                .map(getEntityToDtoMapper()::fromEntityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
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
