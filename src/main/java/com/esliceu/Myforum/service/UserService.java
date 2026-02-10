package com.esliceu.Myforum.service;

import com.esliceu.Myforum.filter.PasswordConverter;
import com.esliceu.Myforum.model.User;
import com.esliceu.Myforum.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        try {
            String hashedPassword = PasswordConverter.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
        return userRepository.save(user);
    }
}
