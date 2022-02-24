package com.infinum.bookpublishingservice.service.security;

import com.infinum.bookpublishingservice.model.security.RoleResolver;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String role) throws UsernameNotFoundException {
        return new User("testUser", "user1!", getAuthority(role));
    }

    private Collection<GrantedAuthority> getAuthority(String role) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(RoleResolver.resolveRole(role).name()));
        return authorities;
    }

}