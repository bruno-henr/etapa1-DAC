package com.example.etapa1DAC;


import com.example.etapa1DAC.domain.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

public class UserSecurity implements UserDetails {

    private static final String PREFIXO_PERMISSION_SPRING = "ROLE_";

    private final Long id;
    private final String email;
    private final String password;
    private final boolean active;
    private final List<SimpleGrantedAuthority> permissions;

    public UserSecurity(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.isActive();
        this.permissions = convertePermissions(user);
    }

    private List<SimpleGrantedAuthority> convertePermissions(User user) {
        return user.getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(PREFIXO_PERMISSION_SPRING + permission.getName()))
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }


}
