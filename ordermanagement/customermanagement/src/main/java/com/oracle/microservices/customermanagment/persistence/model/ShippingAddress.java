package com.oracle.microservices.customermanagment.persistence.model;

import com.oracle.microservices.common.interfaces.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(schema = "ordermanagment",name = "SHIPPING_ADDRESS")
@Getter
@Setter
public class ShippingAddress implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ADDRESSLINE_1", nullable = false)
    private String addressLine1;

    @Column(name = "ADDRESSLINE_2", nullable = false)
    private String addressLine2;

    @Column(name = "PINCODE", nullable = false)
    private Integer pincode;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "COUNTRY", nullable = false)
    private String country;

    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    public ShippingAddress() {

        this.country = "IN";
    }

    public ShippingAddress(String addressLine1, String addressLine2, Integer pincode, String city, String state) {
        this();
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.pincode = pincode;
        this.city = city;
        this.state = state;
    }

}
