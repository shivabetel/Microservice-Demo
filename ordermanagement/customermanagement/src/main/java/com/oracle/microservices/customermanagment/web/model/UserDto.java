package com.oracle.microservices.customermanagment.web.model;

import com.oracle.microservices.common.interfaces.IDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto implements IDto {
    private String emailId;
    private String password;
    private List<AddressDto> shippingAddress;

    public UserDto() {
    }

    public UserDto(String emailId, List<AddressDto> shippingAddress) {
        this.emailId = emailId;
        this.shippingAddress = shippingAddress;
    }
}
