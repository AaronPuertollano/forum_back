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

        if (body.containsKey("name")) {
            user.setName(body.get("name"));
        }

        if (body.containsKey("email")) {
            user.setEmail(body.get("email"));
        }

        userService.save(user);

        UserDTO dto = new UserDTO(user);
        return ResponseEntity.ok(Map.of(
                "token", jwtService.generateToken(user),
                "user", dto
        ));
    }

}
