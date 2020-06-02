package com.oracle.microservices.customermanagment.web.controller;


import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.common.web.dtos.AddressDTO;
import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;
import com.oracle.microservices.customermanagment.service.IShippingAddressService;
import com.oracle.microservices.customermanagment.web.mappers.ShippingAddressEntityDtoMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shippingAddress")
public class ShippingAddressController extends AbstractController<ShippingAddress, Long, AddressDTO> {


    private IShippingAddressService shippingAddressService;
    private IEntityDtoMapper<ShippingAddress, AddressDTO> entityDtoMapper;

    public ShippingAddressController(IShippingAddressService shippingAddressService, ShippingAddressEntityDtoMapper entityDtoMapper) {
        this.shippingAddressService = shippingAddressService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @GetMapping
    public List<AddressDTO> findAll(){
        return this.findAllInternal();
    }

    @PostMapping
    public AddressDTO save(@RequestBody AddressDTO shippingAddress) {
        return this.createOneInternal(shippingAddress);
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
