package com.oracle.microservices.customermanagment.web.controller;

import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.interfaces.ISecuredOperation;
import com.oracle.microservices.common.web.RestPreConditions;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.common.web.dtos.UserDTO;
import com.oracle.microservices.common.web.exception.ResourceNotFoundException;
import com.oracle.microservices.customermanagment.persistence.model.User;
import com.oracle.microservices.customermanagment.service.IUserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController<User, Long, UserDTO> {

    private IUserService userService;
    private IEntityDtoMapper<User, UserDTO> userEntityDtoMapper;
    private ISecuredOperation securedOperation;

    public UserController(IUserService userService, IEntityDtoMapper<User, UserDTO>  userEntityDtoMapper, ISecuredOperation securedOperation) {
        this.userService = userService;
        this.userEntityDtoMapper = userEntityDtoMapper;
        this.securedOperation = securedOperation;
    }

    @GetMapping
    public UserDTO find(@RequestParam(name = "emailId") String emailId){
        return getService().findByEmailId(emailId)
                .map(getEntityToDtoMapper()::fromEntityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @GetMapping("/{id}")
    public UserDTO findByCustomerId(@PathVariable(name = "id") String id){
        RestPreConditions.checkOperationAllowed(
                securedOperation.verifyRequestWithToken("customerId", id));
        UserDTO userDTO = super.findByIdInternal(new Long(id));
        userDTO.setPassword(null);
        return userDTO;
    }

    @PutMapping("/{id}")
    public void update(@RequestBody UserDTO userDTO, @PathVariable(name = "id") String id) {
        RestPreConditions.checkOperationAllowed(
                securedOperation.verifyRequestWithToken("customerId", id));
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
