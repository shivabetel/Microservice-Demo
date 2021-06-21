package com.oracle.microservices.customerordermanagement.persistence.model;


import com.oracle.microservices.common.interfaces.IEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
public class Order implements IEntity {

    public Order(Date orderDate, String orderStatus, User user, ShippingAddress shippingAddress) {
        this.orderDate = orderDate;
        this.user = user;
        this.orderStatus = orderStatus;
        this.shippingAddress = shippingAddress;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "ORDER_DATE", nullable = false)
    private Date orderDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID", nullable = false)
    private User user;

    @Column(name = "ORDER_STATUS", nullable = false)
    private String orderStatus;

    @OneToOne
    @JoinColumn(name = "SHIPPING_ADDRESS_ID")
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetails> details;
}
