package com.example.module4_demo;

import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
@SuppressWarnings("all") // Force-clears all strict IDE null-safety warning checks
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}