package org.ejemplo.controllers;

import org.ejemplo.exceptions.UserAuthenticationException;
import org.ejemplo.exceptions.UserRegistrationException;
import org.ejemplo.models.Login;
import org.ejemplo.models.User;
import org.ejemplo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            userService.register(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario registrado correctamente");
        } catch (UserRegistrationException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        try {
            String role = userService.login(login);
            return ResponseEntity.ok(role);
        } catch (UserAuthenticationException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        }
    }
}

