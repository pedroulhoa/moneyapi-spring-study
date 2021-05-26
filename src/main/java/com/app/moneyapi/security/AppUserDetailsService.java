package com.app.moneyapi.security;

import com.app.moneyapi.entity.User;
import com.app.moneyapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Incorrect username and/or password."));
        return new UserSystem(user, getPermissions(user));
    }

    private Collection<? extends GrantedAuthority> getPermissions(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getPermissions()
                .forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
        return authorities;
    }
}
