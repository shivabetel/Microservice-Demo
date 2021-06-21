package com.oracle.microservices.customerordermanagement.service;

import com.oracle.microservices.common.service.interfaces.IService;
import com.oracle.microservices.customerordermanagement.persistence.model.Order;

import java.util.List;

public interface IOrderService extends IService<Order, Long> {

    List<Order> findOrdersByCustomerId(Long customerId);
}
