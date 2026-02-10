package com.esliceu.Myforum.controller;

import com.esliceu.Myforum.filter.PasswordConverter;
import com.esliceu.Myforum.model.User;
import com.esliceu.Myforum.repo.UserRepository;
import com.esliceu.Myforum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Optional;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) {
        Optional<User> userOpt = userService.findByEmail(data.get("email"));

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        try {
            String hashedInputPassword =
                    PasswordConverter.hashPassword(data.get("password"));

            if (!userOpt.get().getPassword().equals(hashedInputPassword)) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(500).body("Password error");
        }

        return ResponseEntity.ok(userOpt.get());
    }
}

