package com.anon.blogging.Controllers;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anon.blogging.DTO.AuthRequest;
import com.anon.blogging.Service.UserService;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    // Registration — plain REST endpoint, no auth required
    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username and password are required"));
        }
        try {
            userService.registerUser(request.getUsername(), request.getPassword());
            return ResponseEntity.ok(Map.of("message", "Registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error", "Username already taken"));
        }
    }

    // Lets the React app check "am I logged in, and as who?"
    @GetMapping("/api/me")
    public ResponseEntity<?> me(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Not logged in"));
        }
        return ResponseEntity.ok(Map.of("username", principal.getName()));
    }
}
