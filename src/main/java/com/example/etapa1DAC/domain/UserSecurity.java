package com.example.etapa1DAC.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class UserSecurity implements UserDetails {


    private static final String SPRING_PERMISSION_PREFIX = "ROLE_";


    private Long id;
    private String username;
    private String password;

    private List<SimpleGrantedAuthority> authorities;

    private boolean accountNonExpired;
    private  boolean accountNonLocked;
    private  boolean credentialsNonExpired;
    private  boolean enabled;

    public UserSecurity(User user) {

        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
        this.authorities = convertPermissions(user);

        this.accountNonExpired = user.isActive();
        this.accountNonLocked = user.isActive();
        this.credentialsNonExpired = user.isActive();
        this.enabled = user.isActive();

    }

    private List<SimpleGrantedAuthority> convertPermissions(User user) {
        return user.getPermissions().stream()
                .map(permissao -> new SimpleGrantedAuthority(SPRING_PERMISSION_PREFIX + permissao.getName()))
                .collect(Collectors.toList());
    }



}
