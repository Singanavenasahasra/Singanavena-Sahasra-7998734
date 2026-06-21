package com.example.module4_demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
class OrderedTests {

    @Test
    @Order(1) 
    void firstTest() {
        assertTrue(true);
    }

    @Test
    @Order(2) 
    void secondTest() {
        assertNotNull("Java");
    }
}