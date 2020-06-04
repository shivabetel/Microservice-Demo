package com.oracle.microservices.customermanagment.web.controller;


import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.web.controller.AbstractController;
import com.oracle.microservices.common.web.dtos.AddressDTO;
import com.oracle.microservices.common.web.utils.TokenHelper;
import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;
import com.oracle.microservices.customermanagment.service.IShippingAddressService;
import com.oracle.microservices.customermanagment.web.mappers.ShippingAddressEntityDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shippingAddress/{customerId}")
public class ShippingAddressController extends AbstractController<ShippingAddress, Long, AddressDTO> {


    private IShippingAddressService shippingAddressService;
    private IEntityDtoMapper<ShippingAddress, AddressDTO> entityDtoMapper;

    public ShippingAddressController(IShippingAddressService shippingAddressService, ShippingAddressEntityDtoMapper entityDtoMapper) {
        this.shippingAddressService = shippingAddressService;
        this.entityDtoMapper = entityDtoMapper;
    }

    @GetMapping
    public List<AddressDTO> findAllByCustomerId(@PathVariable("customerId") String customerId, @RequestHeader(name = "Authorization") String token){
         token = token.replace("Bearer ", "");
        Map userClaims = TokenHelper.getClaimsByKey("user", token);
        return super.findByParentId(new Long(customerId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO save(@RequestBody AddressDTO shippingAddress, @PathVariable("customerId") String customerId) {
        shippingAddress.setCustomerId(customerId);
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
