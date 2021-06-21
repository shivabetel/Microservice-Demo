package com.oracle.microservices.customermanagment.web.mappers;

import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;
import com.oracle.microservices.customermanagment.persistence.model.User;
import com.oracle.microservices.common.web.dtos.AddressDTO;
import com.oracle.microservices.common.web.dtos.UserDTO;
import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class UserEntityDtoMapper implements IEntityDtoMapper<User, UserDTO> {

    private static Function<ShippingAddress, AddressDTO> addressEntityToDto = (shippingAddress) -> (
            new AddressDTO(shippingAddress.getId(),
                    shippingAddress.getAddressLine1(),
                    shippingAddress.getAddressLine2(),
                    shippingAddress.getPincode(),
                    shippingAddress.getCity(),
                    shippingAddress.getState(),
                    shippingAddress.getCountry())
    );

    private static Function<AddressDTO, ShippingAddress> addressDtoToEntityMapper = (address) -> (
            new ShippingAddress(address.getAddressLine1(),
                    address.getAddressLine2(),
                    address.getPincode(),
                    address.getCity(),
                    address.getState())
    );

    @Override
    public UserDTO fromEntityToDto(User entity) {

        return entity != null ? this.createDto(entity) : null;
    }

    private UserDTO createDto(User entity) {

        return new UserDTO(entity.getId().toString(),
                        entity.getEmailId(),
                        entity.getPassword(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getShippingAddresses()
                        .stream()
                        .map(addressEntityToDto)
                        .collect(Collectors.toList()));
    }

    @Override
    public User fromDtoToEntity(UserDTO dto) {
        return dto != null ? createEntity(dto) : null;
    }

    @Override
    public User updateDtoToEntity(User entity, UserDTO dto) {
        if(entity != null && dto != null) {
            entity.setFirstName(dto.getFirstName());
            entity.setLastName(dto.getLastName());
            Map<Long, ShippingAddress> shippingAddressMap  = entity.getShippingAddresses().stream()
                    .collect(Collectors.toMap(shippingAddress -> shippingAddress.getId(), shippingAddress -> shippingAddress));
            if(dto.getShippingAddress() != null){
                dto.getShippingAddress()
                        .stream()
                        .forEach(address -> {
                            ShippingAddress shippingAddress =  shippingAddressMap.get(address.getAddressId());
                            if(shippingAddress != null){
                                shippingAddress.setAddressLine1(address.getAddressLine1());
                                shippingAddress.setAddressLine2(address.getAddressLine2());
                                shippingAddress.setCity(address.getCity());
                                shippingAddress.setPincode(address.getPincode());
                                shippingAddress.setState(address.getState());
                                shippingAddress.setCountry(address.getCountry());
                            }
                        });
            }


            return entity;
        }
        return null;
    }

    private User createEntity(UserDTO dto) {
        User user = new User(dto.getEmailId(), dto.getPassword(), dto.getFirstName(), dto.getLastName());
        if(dto.getShippingAddress() != null) {
            user.setShippingAddresses(dto.getShippingAddress().stream().map(addressDtoToEntityMapper).collect(Collectors.toList()));
        }
        return user;
    }

}
