package com.esliceu.Myforum.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.esliceu.Myforum.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final JWTService jwtService;

    public JwtFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        boolean isPublic =
                path.equals("/login") ||
                        path.equals("/register") ||
                        request.getMethod().equalsIgnoreCase("OPTIONS");

        String authHeader = request.getHeader("Authorization");

        if (!isPublic && authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            try {
                DecodedJWT jwt = jwtService.validateToken(token);
                String email = jwt.getClaim("email").asString();
                request.setAttribute("email", email);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        // Si es pública, o no hay token, continúa
        filterChain.doFilter(request, response);
    }
}
