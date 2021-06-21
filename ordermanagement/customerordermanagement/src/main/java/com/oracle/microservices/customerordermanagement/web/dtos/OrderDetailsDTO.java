package com.oracle.microservices.customerordermanagement.web.dtos;


import com.oracle.microservices.common.interfaces.IDto;
import com.oracle.microservices.customerordermanagement.persistence.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO implements IDto {

    private String orderDetailsId;
    private ProductDTO product;
    private Integer qty;
    private Float price;
}
