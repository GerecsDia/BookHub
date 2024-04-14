package com.geolidth.BackEnd.auth;

import com.geolidth.BackEnd.exceptions.WrongUsernameOrPasswordException;
import com.geolidth.BackEnd.models.UserRole;
import com.geolidth.BackEnd.models.dao.BookUser;
import com.geolidth.BackEnd.models.dto.NewUser;
import com.geolidth.BackEnd.services.JwtTokenService;
import com.geolidth.BackEnd.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    public AuthController(AuthenticationManager authenticationManager,
                          JwtTokenService jwtTokenService,
                          UserService userService)
    {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
        }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtTokenService.generateToken(authentication);

            return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        } catch (WrongUsernameOrPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody NewUser newUserRequest) {
        String username = newUserRequest.getUsername();
        String password = newUserRequest.getPassword();

        if (userService.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("A felhasználónév már foglalt.");
        }

        if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("A jelszó nem lehet üres.");
        }

        BookUser newUser = new BookUser();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(newUserRequest.getEmail());
        newUser.setRole(UserRole.Role.USER_ROLE);

        if (username.equals("Csajbók-Reményi László") || username.equals("Balász Réka") || username.equals("Gerecs Diána")) {
            newUser.setRole(UserRole.Role.ADMIN_ROLE);
        }
        userService.save(newUser);

        UserDetails userDetails = userService.loadUserByUsername(username);
        String token = jwtTokenService.generateToken(userDetails);

        String successMessage = "Sikeres regisztráció.";

        Map<String, Object> response = new HashMap<>();
        response.put("message", successMessage);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}