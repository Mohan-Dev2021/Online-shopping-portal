package com.online.shop.model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Customer - Main user table which has every information of the user whose are
 * all registered and using our application for shopping experience
 *
 * @author Mohanlal
 * @category Entity module
 */
@Data
@NoArgsConstructor
@Document(collection = "app_customer")
@Accessors(chain = true)
public class Customer implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Id - Primary key
     *
     * @category - Random string UUID
     */
    @Id
    private String id = UUID.randomUUID().toString();

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "last_name")
    private String lastName;

    @Indexed
    @Field(name = "email_id")
    private String emailId;

    @Indexed
    @Field(name = "user_name")
    private String userName;

    @Field(name = "password")
    private String password;

    @Field(name = "contact_no")
    private String contactNo;

    @Field(name = "is_active")
    private boolean isActive;

    @Field(name = "registered_at")
    @CreatedDate
    private LocalDateTime registeredAt;


    @DocumentReference
    private List<Address> addressess;

    private List<Authorities> userAuthorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userAuthorities.stream().map(user -> new SimpleGrantedAuthority(user.getRole()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
