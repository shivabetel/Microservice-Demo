package com.oracle.microservices.authenticationservice.security;

import com.oracle.microservices.common.web.dtos.AddressDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Getter
@Setter
public class User implements UserDetails {

    private String customerId;
    private String emailId;
    private String firstName;
    private String lastName;
    private String password;
    private  String username;
    private  boolean accountNonExpired;
    private  boolean accountNonLocked;
    private  boolean enabled;
    List<AddressDTO> shippingAddress;

    public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked) {
        if (username != null && !"".equals(username) && password != null) {
            this.username = username;
            this.password = password;
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.accountNonLocked = accountNonLocked;
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    public User(String username, String password, String firstName, String lastName, String customerId, String emailId, List<AddressDTO> shippingAddress) {
        this(username, password, true, true, true, true);
        this.firstName = firstName;
        this.lastName = lastName;
        this.customerId = customerId;
        this.emailId = emailId;
        this.shippingAddress = shippingAddress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
