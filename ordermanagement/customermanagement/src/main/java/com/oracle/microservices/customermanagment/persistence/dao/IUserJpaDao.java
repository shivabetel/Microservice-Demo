package com.oracle.microservices.customermanagment.persistence.dao;

import com.oracle.microservices.common.interfaces.dao.IJpaDao;
import com.oracle.microservices.customermanagment.persistence.model.User;

import java.util.Optional;

public interface IUserJpaDao extends IJpaDao<User, Long> {

    Optional<User> findByEmailId(String emailId);
}
