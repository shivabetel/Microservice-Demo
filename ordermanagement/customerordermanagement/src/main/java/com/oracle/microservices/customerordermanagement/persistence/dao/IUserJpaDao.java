package com.oracle.microservices.customerordermanagement.persistence.dao;

import com.oracle.microservices.common.interfaces.dao.IJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.model.User;

public interface IUserJpaDao extends IJpaDao<User, Long> {
}
