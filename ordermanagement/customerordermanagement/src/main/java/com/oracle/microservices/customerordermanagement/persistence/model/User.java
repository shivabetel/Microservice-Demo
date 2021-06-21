package com.oracle.microservices.customerordermanagement.persistence.model;


import com.oracle.microservices.common.interfaces.IEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "ordermanagment",name = "USERS")
@Getter @Setter
public class User implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long id;

    @Column(name = "EMAIL_ID", nullable = false)
    private String emailId;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;


    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled;

    @Column(name = "ACCOUNT_NON_EXPIRED", nullable = false)
    private boolean accountNonExpired;

    @Column(name = "ACCOUNT_NON_LOCKED", nullable = false)
    private boolean accountNonLocked;


    public User() {

        this.accountNonExpired = Boolean.TRUE;
        this.accountNonLocked = Boolean.TRUE;
        this.enabled = Boolean.TRUE;//making true default, if user enabling process is there?(via email), then need to remove it
    }

    public User(String emailId, String password, String firstName, String lastName) {
        this();
        this.emailId = emailId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;

    }
}
