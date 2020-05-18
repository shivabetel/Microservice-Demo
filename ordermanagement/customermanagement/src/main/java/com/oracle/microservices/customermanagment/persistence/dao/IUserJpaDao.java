package com.oracle.microservices.customermanagment.persistence.dao;

import com.oracle.microservices.common.entities.User;
import com.oracle.microservices.common.interfaces.dao.IJpaDao;

import java.util.Optional;

public interface IUserJpaDao extends IJpaDao<User, Long> {

    Optional<User> findByEmailId(String emailId);
}
