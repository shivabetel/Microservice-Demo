package com.oracle.microservices.customermanagment.web.mappers;

import com.oracle.microservices.customermanagment.persistence.model.ShippingAddress;
import com.oracle.microservices.customermanagment.persistence.model.User;
import com.oracle.microservices.common.web.dtos.AddressDTO;
import com.oracle.microservices.common.web.dtos.UserDTO;
import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class UserEntityDtoMapper implements IEntityDtoMapper<User, UserDTO> {

    private static Function<ShippingAddress, AddressDTO> addressEntityToDto = (shippingAddress) -> (
            new AddressDTO(shippingAddress.getAddressLine1(),
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

    private User createEntity(UserDTO dto) {
        User user = new User(dto.getEmailId(), dto.getPassword(), dto.getFirstName(), dto.getLastName());
        user.setShippingAddresses(dto.getShippingAddress().stream().map(addressDtoToEntityMapper).collect(Collectors.toList()));
        return user;
    }
}
