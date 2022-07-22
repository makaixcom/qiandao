package com.makaix.qiandao.config.security;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Setter
public class MakaixUserDetail implements UserDetails {

    private Long id;

    private List<GrantedAuthority> resources = new ArrayList<>();

    private Boolean disabled;

    private Boolean locked;

    private Boolean expired;

    private Boolean credentialsExpired;

    public List<GrantedAuthority> getResources() {
        return resources;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return resources;
    }

    @Override
    public String getPassword() {
        return "$2a$10$V5mRMGbaoemCyScuOFOYj.B/I9jgYNylSrJPR41CkYG5O9kLmYcCy";
    }

    @Override
    public String getUsername() {
        return "张三";
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return !disabled;
    }
}
