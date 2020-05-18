package com.oracle.microservices.customermanagment.service;

import com.oracle.microservices.common.entities.User;
import com.oracle.microservices.common.service.interfaces.IService;

import java.util.Optional;

public interface IUserService extends IService<User, Long> {

    Optional<User> findByEmailId(String emailId);
}
