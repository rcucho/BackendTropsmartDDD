package com.techelper.tropsmart_backend.configuration.domain.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techelper.tropsmart_backend.user.domain.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Integer id, String username, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(User user) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        String username = user.getPerson().getFirstName()+" "+user.getPerson().getLastName();

        /*user.getPermissions().forEach(permission -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(permission.getName().name());
            authorities.add(authority);
        });
        // Extract list of roles (ROLE_name)
        user.getRoles().forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getName().name());
            authorities.add(authority);
        });*/


        return new UserPrinciple(
                user.getId(),
                username,
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }
}
