package com.oracle.microservices.common.web.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oracle.microservices.common.interfaces.IDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO implements IDto {

    private Long addressId;
    private String addressLine1;
    private String addressLine2;
    private Integer pincode;
    private String city;
    private String state;
    private String country;
    @JsonIgnore
    private String customerId;

    public AddressDTO() {
    }

    public AddressDTO(Long addressId, String addressLine1, String addressLine2, Integer pincode, String city, String state, String country) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
