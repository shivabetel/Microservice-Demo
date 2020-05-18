package com.oracle.microservices.customermanagment.web.mappers;

import com.oracle.microservices.common.entities.ShippingAddress;
import com.oracle.microservices.common.entities.User;
import com.oracle.microservices.customermanagment.web.model.AddressDto;
import com.oracle.microservices.customermanagment.web.model.UserDto;
import com.oracle.microservices.common.interfaces.IEntityDtoMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class UserEntityDtoMapper implements IEntityDtoMapper<User, UserDto> {

    private static Function<ShippingAddress, AddressDto> addressEntityToDto = (shippingAddress) -> (
            new AddressDto(shippingAddress.getAddressLine1(),
                    shippingAddress.getAddressLine2(),
                    shippingAddress.getPincode(),
                    shippingAddress.getCity(),
                    shippingAddress.getState(),
                    shippingAddress.getCountry())
    );

    private static Function<AddressDto, ShippingAddress> addressDtoToEntityMapper = (address) -> (
            new ShippingAddress(address.getAddressLine1(),
                    address.getAddressLine2(),
                    address.getPincode(),
                    address.getCity(),
                    address.getState())
    );

    @Override
    public UserDto fromEntityToDto(User entity) {

         return entity != null ? this.createDto(entity) : null;
    }

    private UserDto createDto(User entity) {

        return new UserDto(entity.getEmailId(),
                entity.getShippingAddresses()
                        .stream()
                        .map(addressEntityToDto)
                        .collect(Collectors.toList()));
    }

    @Override
    public User fromDtoToEntity(UserDto dto) {
          return  dto != null ? createEntity(dto) : null;
    }

    private User createEntity(UserDto dto) {
        User user = new User(dto.getEmailId(), dto.getPassword());
        user.setShippingAddresses(dto.getShippingAddress().stream().map(addressDtoToEntityMapper).collect(Collectors.toList()));
        return user;
    }
}
