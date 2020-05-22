package com.oracle.microservices.customermanagment.service;

import com.oracle.microservices.common.service.interfaces.IService;
import com.oracle.microservices.customermanagment.persistence.model.User;

import java.util.Optional;

public interface IUserService extends IService<User, Long> {

    Optional<User> findByEmailId(String emailId);
}
