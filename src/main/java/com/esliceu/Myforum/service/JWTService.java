package com.esliceu.Myforum.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.esliceu.Myforum.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class JWTService {

    @Value("${jwtsecret}")
    String jwtsecret;

    public String generateToken(User user) {
        return JWT.create()
                .withClaim("id", user.getId().toString())
                .withClaim("_id", user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("name", user.getName())
                .withClaim("role", user.getRole())
                .withClaim("avatarUrl", "")
                .withClaim("__v", 0)
                .withClaim("permissions", Map.of(
                        "root", List.of(
                                "own_topics:write",
                                "own_topics:delete",
                                "own_replies:write",
                                "own_replies:delete"
                        ),
                        "categories", List.of()
                ))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .sign(Algorithm.HMAC256(jwtsecret));
    }

    public DecodedJWT validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(jwtsecret))
                .build()
                .verify(token);
    }
}
