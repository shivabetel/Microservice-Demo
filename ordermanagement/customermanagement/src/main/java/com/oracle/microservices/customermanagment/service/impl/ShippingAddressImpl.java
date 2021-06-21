package com.oracle.microservices.customermanagment.service.impl;

import com.oracle.microservices.common.search.ClientOperation;
import com.oracle.microservices.common.service.AbstractService;
import com.oracle.microservices.customermanagment.persistence.dao.IShippingAddressJpaDao;
import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;
import com.oracle.microservices.customermanagment.persistence.model.User;
import com.oracle.microservices.customermanagment.service.IShippingAddressService;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;


@Service
public class ShippingAddressImpl extends AbstractService<ShippingAddress, Long> implements IShippingAddressService {

    private IShippingAddressJpaDao shippingAddressJpaDao;

    public ShippingAddressImpl(IShippingAddressJpaDao shippingAddressJpaDao) {
        this.shippingAddressJpaDao = shippingAddressJpaDao;
    }


    @Override
    public List<ShippingAddress> findAddressByCustomerId(Long customerId) {
        return getDao().findAddressByCustomerId(customerId);
    }

    @Override
    public Optional<ShippingAddress> findById(Long customerId, Long addressId) {
        return getDao().findById(customerId, addressId);
    }

//    @Override
//    public Specification<ShippingAddress> resolveConstraint(Triple<String, ClientOperation, String> constraint) {
//        String constraintName = constraint.getLeft();
//        if(constraintName.equals("customerId")){
//            return (Specification<ShippingAddress>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("customerId"), Long.parseLong(constraint.getRight()));
//        }else if(constraintName.equals("addressId")){
//            return (Specification<ShippingAddress>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), Long.parseLong(constraint.getRight()));
//        }
//
//        return null;
//    }

    @Override
    public IShippingAddressJpaDao getDao() {
        return this.shippingAddressJpaDao;
    }

    @Override
    protected JpaSpecificationExecutor<ShippingAddress> getSpecificationExecutor() {
        return shippingAddressJpaDao;
    }
}
