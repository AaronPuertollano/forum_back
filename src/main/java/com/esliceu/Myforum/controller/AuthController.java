package com.esliceu.Myforum.controller;

import com.esliceu.Myforum.dto.UserDTO;
import com.esliceu.Myforum.filter.PasswordConverter;
import com.esliceu.Myforum.model.User;
import com.esliceu.Myforum.repo.UserRepository;
import com.esliceu.Myforum.service.JWTService;
import com.esliceu.Myforum.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        //MODIFICAR TODOS DEBEN DE TENER PERMISOS COMO ADMINISTRADORES

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

        User user = userOpt.get();

        try {
            String passwordHashed = PasswordConverter.hashPassword(data.get("password"));

            if (!userOpt.get().getPassword().equals(passwordHashed)) {
                return ResponseEntity.status(401).body("Invalid credentials");
            }

        } catch (NoSuchAlgorithmException e) {
            return ResponseEntity.status(500).body("Password error");
        }

        String token = jwtService.generateToken(user);


        UserDTO userDTO = new UserDTO(user);

        return ResponseEntity.ok(Map.of(
                "token", token,
                "user", userDTO
        ));
    }

    @GetMapping("/getprofile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {

        String email = (String) request.getAttribute("email");

        if (email == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        return userService.findByEmail(email)
                .map(user -> {
                    UserDTO dto = new UserDTO(user);
                    return ResponseEntity.ok(dto);
                })
                .orElse(ResponseEntity.notFound().build());
    }


}

