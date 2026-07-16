package com.example.module4_demo;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class ExceptionThrowerTest {
    private final ExceptionThrower thrower = new ExceptionThrower();

    @Test
    void testThrowException() {
        assertThrows(IllegalArgumentException.class, () -> thrower.throwException()); 
    }
}