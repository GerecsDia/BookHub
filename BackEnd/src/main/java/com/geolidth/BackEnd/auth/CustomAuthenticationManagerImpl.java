package com.geolidth.BackEnd.auth;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationManagerImpl implements CustomAuthenticationManager {

    private final AuthenticationManager authenticationManager;

    public CustomAuthenticationManagerImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResult authenticate(UserCredentials userCredentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userCredentials.getUsername(),
                            userCredentials.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new AuthenticationResult(true, authentication);
        } catch (AuthenticationServiceException e) {
            return new AuthenticationResult(false, null);
        }
    }
}