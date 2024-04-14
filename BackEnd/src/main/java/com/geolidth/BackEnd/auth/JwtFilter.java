package com.geolidth.BackEnd.auth;

import com.geolidth.BackEnd.exceptions.NoSuchUserException;
import com.geolidth.BackEnd.models.dao.BookUser;
import com.geolidth.BackEnd.repositories.BookUserRepository;
import com.geolidth.BackEnd.services.TokenService;
import com.geolidth.BackEnd.services.UserService;
import lombok.AllArgsConstructor;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private BookUserRepository bookUserRepository;
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if (isPublicEndpoint(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String auth = request.getHeader(AUTHORIZATION_HEADER);
        if (auth != null && auth.startsWith("Bearer ")) {
            String token = auth.substring(7);
            if (tokenService.isValid(token)) {
                String username = tokenService.extractUsername(token);
                setSecurityContext(username);
            }
        }
        filterChain.doFilter(request, response);
    }

    public void setSecurityContext(String username) {
        try {
            BookUser user = bookUserRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Nem található ilyen felhasználó: " + username));
            Authentication auth = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (UsernameNotFoundException e) {
            logger.error("Felhasználó nem található: " + username, e);
            SecurityContextHolder.getContext().setAuthentication(null);
        } catch (NoSuchUserException e) {
            logger.error("Nem létező felhasználó: " + username, e);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }

    public boolean isPublicEndpoint(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        if (request.getMethod().equals("POST") && servletPath.equals("/users")) {
            return true;
        }
        if (request.getMethod().equals("POST") && servletPath.equals("/login")) {
            return true;
        }
        if (servletPath.startsWith("/guest")) {
            return true;
        }
        return SecurityContextHolder.getContext().getAuthentication() == null
                || ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities().stream()
                .noneMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    }
}