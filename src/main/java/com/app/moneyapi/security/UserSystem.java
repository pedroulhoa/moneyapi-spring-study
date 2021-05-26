package com.app.moneyapi.security;

import com.app.moneyapi.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserSystem extends org.springframework.security.core.userdetails.User {

    private User user;

    public UserSystem(User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
