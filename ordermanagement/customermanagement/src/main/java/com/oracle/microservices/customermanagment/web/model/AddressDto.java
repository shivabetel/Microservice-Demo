package com.oracle.microservices.customermanagment.web.model;


import com.oracle.microservices.common.interfaces.IDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto implements IDto {

    private String addressLine1;
    private String addressLine2;
    private Integer pincode;
    private String city;
    private String state;
    private String country;

    public AddressDto() {
    }

    public AddressDto(String addressLine1, String addressLine2, Integer pincode, String city, String state, String country) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
        this.country = country;
    }
}
