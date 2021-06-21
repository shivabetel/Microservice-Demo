package com.oracle.microservices.customerordermanagement.persistence.dao;

import com.oracle.microservices.common.interfaces.dao.IJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.model.Order;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderJpaDao extends IJpaDao<Order, Long> {
    @Query("select o from Order o where o.user.id=:customerId")
    List<Order> findOrdersByCustomerId(Long customerId);
}
