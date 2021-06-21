package com.oracle.microservices.customerordermanagement.web.dtos;

import com.oracle.microservices.common.interfaces.IDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.File;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements IDto {

    private String productId;
    private String productName;
    private String description;
    private String image;
    private String category;
    private String price;
}
