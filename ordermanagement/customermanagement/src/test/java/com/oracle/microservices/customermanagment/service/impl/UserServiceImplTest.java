package com.oracle.microservices.customermanagment.service.impl;

import com.oracle.microservices.commontest.service.AbstractServiceIntegrationTest;
import com.oracle.microservices.customermanagment.persistence.model.User;
import com.oracle.microservices.customermanagment.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

class UserServiceImplTest extends AbstractServiceIntegrationTest<User, Long> {

    @Autowired
    IUserService service;

    @Override
    protected IUserService getApi() {
        return service;
    }

    @Override
    protected User createEntity() {
        return new User("shiva.betel@gmail.com", "password", "shivakumar", "GM");
    }
}