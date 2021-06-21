package com.oracle.microservices.customerordermanagement.service;

import com.oracle.microservices.common.service.interfaces.IService;
import com.oracle.microservices.customerordermanagement.persistence.model.Product;

public interface IProductService extends IService<Product, Long> {
}
