package com.oracle.microservices.customermanagment.web.controller;

import com.oracle.microservices.common.web.exception.OperationForbiddenException;
import com.oracle.microservices.common.web.utils.TokenHelper;
import com.oracle.microservices.customermanagment.persistence.model.User;
import com.oracle.microservices.customermanagment.service.IUserService;
import com.oracle.microservices.common.web.dtos.UserDTO;
import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.common.web.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController<User, Long, UserDTO> {

    private IUserService userService;
    private IEntityDtoMapper<User, UserDTO> userEntityDtoMapper;

    public UserController(IUserService userService, IEntityDtoMapper<User, UserDTO>  userEntityDtoMapper) {
        this.userService = userService;
        this.userEntityDtoMapper = userEntityDtoMapper;
    }

    @GetMapping
    public UserDTO find(@RequestParam(name = "emailId") String emailId){
        return getService().findByEmailId(emailId)
                .map(getEntityToDtoMapper()::fromEntityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @GetMapping("/{id}")
    public UserDTO findByCustomerId(@PathVariable(name = "id") String id, @RequestHeader(name = "Authorization") String token){
        token = token.replace("Bearer ", "");
        Map userClaims = TokenHelper.getClaimsByKey("user", token);
        if(!(String.valueOf(userClaims.get("customerId")).equals(id))){
            throw new OperationForbiddenException("Operation not allowed");
        }
        UserDTO userDTO = super.findByIdInternal(new Long(id));
        userDTO.setPassword(null);
        return userDTO;
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UserDTO userDTO, @PathVariable(name = "id") String id, @RequestHeader(name = "Authorization") String token) {
        token = token.replace("Bearer ", "");
        Map userClaims = TokenHelper.getClaimsByKey("user", token);
        if(!(String.valueOf(userClaims.get("customerId")).equals(id))){
            throw new OperationForbiddenException("Operation not allowed");
        }
        super.updateInternal(new Long(id), userDTO);
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
