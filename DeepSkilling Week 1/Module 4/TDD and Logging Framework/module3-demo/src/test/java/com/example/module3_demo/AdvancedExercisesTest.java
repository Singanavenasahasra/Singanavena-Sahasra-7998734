package com.example.module3_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestMethodOrder(OrderAnnotation.class)
public class AdvancedExercisesTest {

    // Exercise 1
    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10})
    @Order(1)
    public void exercise1(int number) {
        EvenChecker checker = new EvenChecker();
        assertTrue(checker.isEven(number));
    }

    // Exercise 2 & 3
    @Test
    @Order(2)
    public void exercise3_first() {
        assertEquals(1, 1);
    }

    @Test
    @Order(3)
    public void exercise3_second() {
        assertEquals(2, 2);
    }

    // Exercise 4
    @Test
    @Order(4)
    public void exercise4() {
        ExceptionThrower thrower = new ExceptionThrower();
        try {
            thrower.throwException();
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertNotNull(e.getMessage());
        }
    }

    // Exercise 5
    @Test
    @Order(5)
    public void exercise5() {
        PerformanceTester tester = new PerformanceTester();
        
        long startTime = System.currentTimeMillis();
        tester.performTask();
        long duration = System.currentTimeMillis() - startTime;
        
        assertTrue(duration < 500);
    }
}