package com.oracle.microservices.customermanagment.service;

import com.oracle.microservices.common.service.interfaces.IService;
import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;

import java.util.List;
import java.util.Optional;

public interface IShippingAddressService extends IService<ShippingAddress, Long> {

    List<ShippingAddress> findAddressByCustomerId(Long customerId);
    Optional<ShippingAddress> findById(Long customerId, Long addressId);

}
