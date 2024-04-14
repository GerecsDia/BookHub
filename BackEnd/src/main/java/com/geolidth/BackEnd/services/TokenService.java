package com.geolidth.BackEnd.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    String generateToken(UserDetails user);

    boolean isValid(String token);

    String extractUsername(String token);

}
