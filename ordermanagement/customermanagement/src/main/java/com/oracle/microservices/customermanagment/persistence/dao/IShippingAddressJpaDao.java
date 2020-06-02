package com.oracle.microservices.customermanagment.persistence.dao;

import com.oracle.microservices.common.interfaces.dao.IJpaDao;
import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;

public interface IShippingAddressJpaDao extends IJpaDao<ShippingAddress, Long> {
}
