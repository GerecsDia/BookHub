package com.geolidth.BackEnd.services;

import com.geolidth.BackEnd.models.dao.BookUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.util.Date;


@Service
public class JwtTokenService implements TokenService {
    @Value("${token.jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;



    public String generateToken(Authentication authentication) {
        BookUser user = (BookUser) authentication.getPrincipal();
        Instant now = Instant.now();
        Instant expirationTime = now.plusSeconds(jwtExpirationMs / 1000);
        return Jwts.builder()
                .claim("username", user.getUsername())
                .setIssuedAt(Date.from(now))            //Frissíteni kell
                .setExpiration(Date.from(expirationTime))           //Frissíteni kell
                .signWith(getSigningKey())
                .compact();
    }


    @Override
    public String generateToken(UserDetails user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        return generateToken(authentication);
    }

    @Override
    public boolean isValid(String token) {
        Claims claims = getAllClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.after(new Date());
    }

    @Override
    public String extractUsername(String token) {
        Claims claims = getAllClaims(token);
        return claims.getSubject();
    }

    public Claims getAllClaims(String token) {
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        JwtParser parser = Jwts.parser()
                .setSigningKey(key) //Frissíteni kell
                .build();
        return parser.parseClaimsJws(token).getBody();  //Frissíteni kell
    }
    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
