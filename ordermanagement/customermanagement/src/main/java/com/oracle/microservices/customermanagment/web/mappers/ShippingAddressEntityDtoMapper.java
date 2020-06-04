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

    @Override
    public ShippingAddress updateDtoToEntity(ShippingAddress entity, AddressDTO dto) {
        entity.setAddressLine1(dto.getAddressLine1());
        entity.setAddressLine2(dto.getAddressLine2());
        entity.setCity(dto.getCity());
        entity.setPincode(dto.getPincode());
        entity.setState(dto.getState());
        entity.setCountry(dto.getCountry());
        return entity;
    }

    private AddressDTO createDto(ShippingAddress entity) {
        return new AddressDTO(entity.getId(),
                entity.getAddressLine1(),
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
        shippingAddress.setCustomerId(new Long(dto.getCustomerId()));
        return shippingAddress;
    }

}
