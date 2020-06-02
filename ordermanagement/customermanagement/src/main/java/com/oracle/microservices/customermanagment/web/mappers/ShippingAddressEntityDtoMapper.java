package com.oracle.microservices.customermanagment.web.mappers;

import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import com.oracle.microservices.common.web.dtos.AddressDTO;
import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;
import org.springframework.stereotype.Component;


@Component
public class ShippingAddressEntityDtoMapper implements IEntityDtoMapper<ShippingAddress, AddressDTO> {

    @Override
    public AddressDTO fromEntityToDto(ShippingAddress entity) {
        return createDto(entity);
    }

    @Override
    public ShippingAddress fromDtoToEntity(AddressDTO dto) {
        return createEntity(dto);
    }

    private AddressDTO createDto(ShippingAddress entity) {
        return new AddressDTO(entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getPincode(),
                entity.getCity(),
                entity.getState(),
                entity.getCountry());
    }

    private ShippingAddress createEntity(AddressDTO dto) {
        ShippingAddress shippingAddress = new ShippingAddress(dto.getAddressLine1(),
                dto.getAddressLine2(),
                dto.getPincode(),
                dto.getCity(),
                dto.getState());
        shippingAddress.setCustomerId(new Long(1));
        return shippingAddress;
    }
}
