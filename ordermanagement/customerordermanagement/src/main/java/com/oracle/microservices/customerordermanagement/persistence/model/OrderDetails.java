package com.oracle.microservices.customerordermanagement.persistence.model;

import com.oracle.microservices.common.interfaces.IEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_DETAILS")
@Getter
@Setter
@NoArgsConstructor
public class OrderDetails implements IEntity {

    public OrderDetails(Integer qty, Product product, Order order) {
        this.qty = qty;
        this.product = product;
        this.order = order;
    }

    public OrderDetails(Product product,Integer qty, Float totalPrice) {
        this.qty = qty;
        this.product = product;
        this.totalPrice = totalPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_DTLS_ID")
    private Long id;

    @Column(name = "QTY")
    private Integer qty;

    private Float totalPrice;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private Product product;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDER_ID")
    private Order order;



}
