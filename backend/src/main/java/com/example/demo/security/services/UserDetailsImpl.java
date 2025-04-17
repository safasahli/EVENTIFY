package com.example.demo.security.services;

import com.example.demo.entities.User;
import com.example.demo.entities.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructor to initialize UserDetailsImpl from a User entity
    public UserDetailsImpl(Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    // Factory method to create UserDetailsImpl from a User entity
    public static UserDetailsImpl build(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name())); // Convert Role to SimpleGrantedAuthority
        }

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),  // Use username directly
                user.getEmail(),     // Use email directly
                user.getPasswordHash(),  // Use password hash
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;  // You can return any field, commonly email is used
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can implement more logic to handle expired accounts if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You can implement more logic to handle locked accounts if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can implement more logic to handle expired credentials if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // You can implement more logic to handle disabled accounts if needed
    }
}
