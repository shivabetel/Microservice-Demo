package com.oracle.microservices.customermanagment.service.impl;

import com.oracle.microservices.common.service.AbstractService;
import com.oracle.microservices.customermanagment.persistence.dao.IShippingAddressJpaDao;
import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;
import com.oracle.microservices.customermanagment.service.IShippingAddressService;
import org.springframework.stereotype.Service;


@Service
public class ShippingAddressImpl extends AbstractService<ShippingAddress, Long> implements IShippingAddressService {

    @Override
    public IShippingAddressJpaDao getDao() {
        return null;
    }
}
