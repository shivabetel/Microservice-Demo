package com.oracle.microservices.customerordermanagement.persistence.model;


import com.oracle.microservices.common.interfaces.IEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
public class Product implements IEntity {

    public Product(String name, String description, boolean active, String image, ProductCategory category) {
        this.name = name;
        this.description = description;
        this.active = active;
        this.image = image;
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String name;

    @Lob
    @Column(name = "PRODUCT_DESCRIPTION", nullable = false)
    private String description;

    private boolean active;

    @Lob
    private String image;

    private Long price;

    @OneToOne
    @JoinColumn(name = "CATEGORY", referencedColumnName = "PRODUCT_CATEGORY_CD", nullable = false)
    private ProductCategory category;

}
