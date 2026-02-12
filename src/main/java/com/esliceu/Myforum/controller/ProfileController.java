package com.esliceu.Myforum.controller;

import com.esliceu.Myforum.dto.UserDTO;
import com.esliceu.Myforum.model.User;
import com.esliceu.Myforum.service.JWTService;
import com.esliceu.Myforum.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTService jwtService;

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody Map<String, String> body,
                                           HttpServletRequest request) {

        String email = (String) request.getAttribute("email");
        if (email == null) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        Optional<User> userOpt = userService.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User user = userOpt.get();

        // Actualizar campos si vienen en el body
        if (body.containsKey("name")) {
            user.setName(body.get("name"));
        }
        if (body.containsKey("email")) {
            // Opcional: validar que no exista otro usuario con ese email
            String newEmail = body.get("email");
            if (!newEmail.equals(user.getEmail()) && userService.findByEmail(newEmail).isPresent()) {
                return ResponseEntity.badRequest().body("Email already in use");
            }
            user.setEmail(newEmail);
        }

        userService.save(user);

        UserDTO dto = new UserDTO(user);
        return ResponseEntity.ok(Map.of(
                "token", jwtService.generateToken(user), // actualizar token con nuevo email
                "user", dto
        ));
    }

}
