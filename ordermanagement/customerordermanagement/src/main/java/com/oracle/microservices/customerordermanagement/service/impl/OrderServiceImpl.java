package com.oracle.microservices.customerordermanagement.service.impl;

import com.oracle.microservices.common.interfaces.dao.IJpaDao;
import com.oracle.microservices.common.service.AbstractService;
import com.oracle.microservices.customerordermanagement.persistence.dao.IOrderJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.model.Order;
import com.oracle.microservices.customerordermanagement.service.IOrderService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl extends AbstractService<Order, Long> implements IOrderService {

    private IOrderJpaDao dao;

    public OrderServiceImpl(IOrderJpaDao dao) {
        this.dao = dao;
    }


    @Override
    public List<Order> findOrdersByCustomerId(Long customerId) {
        return getDao().findOrdersByCustomerId(customerId);
    }

    @Override
    public IOrderJpaDao getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Order> getSpecificationExecutor() {
        return dao;
    }
}
