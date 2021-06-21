package com.oracle.microservices.customerordermanagement.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oracle.microservices.common.interfaces.IDto;
import com.oracle.microservices.common.web.dtos.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO implements IDto {

    private String orderId;
    private String orderDate;
    private String orderStatus;
    List<OrderDetailsDTO> orderDetails = new ArrayList<>();
    @JsonIgnore
    private String customerId;

    private AddressDTO address;

    public OrderDTO(String orderId, String orderDate, String orderStatus, List<OrderDetailsDTO> orderDetails, AddressDTO address) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderDetails = orderDetails;
        this.address = address;
    }
}
