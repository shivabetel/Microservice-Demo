package com.oracle.microservices.customerordermanagement.persistence.model;


import com.oracle.microservices.common.interfaces.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_CATEGORY")
@Getter
@Setter
public class ProductCategory implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_CATEGORY_CD")
    private String id;

    @Column(name = "PRODUCT_CATEGORY_NAME")
    private String name;

    @Column(name = "PRODUCT_CATEGORY_DESC")
    private String description;


}
