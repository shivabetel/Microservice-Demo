package com.oracle.microservices.customerordermanagement.persistence.dao;

import com.oracle.microservices.common.interfaces.dao.IJpaDao;
import com.oracle.microservices.customerordermanagement.persistence.model.ShippingAddress;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IShippingAddressJpaDao extends IJpaDao<ShippingAddress, Long> {

    @Query("select address from ShippingAddress address inner join User user on address.customerId = user.id where address.customerId=:customerId")
    List<ShippingAddress> findAddressByCustomerId(Long customerId);

    @Query("select address from ShippingAddress address inner join User user on address.customerId = user.id where user.id=:customerId and address.id=:addressId")
    Optional<ShippingAddress> findById(Long customerId, Long addressId);
}
