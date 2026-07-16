package com.example.module4_demo;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock 
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUserByIdSuccess() {
        User user = new User(495L, "S Bhavana");
        when(userRepository.findById(495L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(495L);
        assertNotNull(result);
        assertEquals("S Bhavana", result.getName());
    }

    @Test
    void testGetUserByIdThrowsException() {
        when(userRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> userService.getUserById(999L)); 
    }
}