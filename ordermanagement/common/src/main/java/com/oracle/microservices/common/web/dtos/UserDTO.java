package com.oracle.microservices.common.web.dtos;

import com.oracle.microservices.common.interfaces.IDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements IDto {
    private String customerId;
    private String emailId;
    private String password;
    private String firstName;
    private String lastName;
    private List<AddressDTO> shippingAddress = new ArrayList<>();

}
