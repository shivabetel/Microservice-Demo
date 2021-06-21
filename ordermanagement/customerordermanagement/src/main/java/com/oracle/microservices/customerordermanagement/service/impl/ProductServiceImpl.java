package com.oracle.microservices.customerordermanagement.service.impl;

import com.oracle.microservices.common.interfaces.dao.IJpaDao;
import com.oracle.microservices.common.service.AbstractService;
import com.oracle.microservices.customerordermanagement.persistence.dao.IProductJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.model.Product;
import com.oracle.microservices.customerordermanagement.service.IProductService;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl extends AbstractService<Product, Long> implements IProductService {

    private IProductJpaDao dao;

    public ProductServiceImpl(IProductJpaDao dao) {
        this.dao = dao;
    }

    @Override
    public IJpaDao<Product, Long> getDao() {
        return dao;
    }

    @Override
    protected JpaSpecificationExecutor<Product> getSpecificationExecutor() {
        return dao;
    }
}
