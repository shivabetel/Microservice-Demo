package com.oracle.microservices.customermanagment.web.controller;


import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.interfaces.ISecuredOperation;
import com.oracle.microservices.common.search.ClientOperation;
import com.oracle.microservices.common.web.RestPreConditions;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.common.web.dtos.AddressDTO;
import com.oracle.microservices.common.web.exception.ResourceNotFoundException;
import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;
import com.oracle.microservices.customermanagment.service.IShippingAddressService;
import com.oracle.microservices.customermanagment.web.mappers.ShippingAddressEntityDtoMapper;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shippingAddress/{customerId}")
public class ShippingAddressController extends AbstractController<ShippingAddress, Long, AddressDTO> {


    private IShippingAddressService shippingAddressService;
    private IEntityDtoMapper<ShippingAddress, AddressDTO> entityDtoMapper;
    private ISecuredOperation securedOperation;

    public ShippingAddressController(IShippingAddressService shippingAddressService,
                                     ShippingAddressEntityDtoMapper entityDtoMapper,
                                     ISecuredOperation securedOperation) {
        this.shippingAddressService = shippingAddressService;
        this.entityDtoMapper = entityDtoMapper;
        this.securedOperation = securedOperation;
    }

    @GetMapping
    public List<AddressDTO> findAddressByCustomerId(@PathVariable("customerId") String customerId){
        RestPreConditions.checkOperationAllowed(
                securedOperation.verifyRequestWithToken("customerId", customerId));
        return getService().findAddressByCustomerId(Long.valueOf(customerId))
                .stream()
                .map(getEntityToDtoMapper()::fromEntityToDto )
                .collect(Collectors.toList());
    }

    @GetMapping("/{addressId}")
    public AddressDTO find(@PathVariable("customerId") String customerId, @PathVariable("addressId") String addressId){
        RestPreConditions.checkOperationAllowed(
                securedOperation.verifyRequestWithToken("customerId", customerId));
        return getService().findById(Long.valueOf(customerId), Long.valueOf(addressId))
                .map(getEntityToDtoMapper()::fromEntityToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Address details not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO save(@RequestBody AddressDTO shippingAddress, @PathVariable("customerId") String customerId) {
        RestPreConditions.checkOperationAllowed(
                securedOperation.verifyRequestWithToken("customerId", customerId));
        shippingAddress.setCustomerId(customerId);
        return this.createOneInternal(shippingAddress);
    }

    @PutMapping("/{addressId}")
    public void update(@RequestBody AddressDTO shippingAddress, @PathVariable("customerId") String customerId,
                             @PathVariable("addressId") String addressId) {
        RestPreConditions.checkOperationAllowed(
                securedOperation.verifyRequestWithToken("customerId", customerId));
        shippingAddress.setCustomerId(customerId);
         this.updateInternal(new Long(addressId), shippingAddress);
    }

    @Override
    public IShippingAddressService getService() {
        return shippingAddressService;
    }

    @Override
    public IEntityDtoMapper<ShippingAddress, AddressDTO> getEntityToDtoMapper() {
        return entityDtoMapper;
    }
}
