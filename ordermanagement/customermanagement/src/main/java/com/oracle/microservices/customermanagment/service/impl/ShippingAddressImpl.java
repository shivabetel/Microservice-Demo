package com.oracle.microservices.customermanagment.service.impl;

import com.oracle.microservices.common.service.AbstractService;
import com.oracle.microservices.customermanagment.persistence.dao.IShippingAddressJpaDao;
import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;
import com.oracle.microservices.customermanagment.service.IShippingAddressService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShippingAddressImpl extends AbstractService<ShippingAddress, Long> implements IShippingAddressService {

    private IShippingAddressJpaDao shippingAddressJpaDao;

    public ShippingAddressImpl(IShippingAddressJpaDao shippingAddressJpaDao) {
        this.shippingAddressJpaDao = shippingAddressJpaDao;
    }

    @Override
    public List<ShippingAddress> findByParentId(Long customerId) {
        return getDao().findByCustomerId(customerId);
    }


    @Override
    public IShippingAddressJpaDao getDao() {
        return this.shippingAddressJpaDao;
    }
}
