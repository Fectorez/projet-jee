package com.esgi.projetjee.auth;

import com.esgi.projetjee.service.UserService;
import com.esgi.projetjee.utils.BCryptManagerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AppAuthProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;

        String name = auth.getName();
        String password = auth.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(name);

        if ( user == null ) {
            System.out.println("username false");
            throw new BadCredentialsException("Username/Password does not match for " + auth.getPrincipal());
        }

        else {
            boolean passwordMatches = BCryptManagerUtil.passwordencoder().matches(password, user.getPassword());
            if ( !passwordMatches ) {
                throw new BadCredentialsException("Username/Password does not match for " + "s");
            }
        }

        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
