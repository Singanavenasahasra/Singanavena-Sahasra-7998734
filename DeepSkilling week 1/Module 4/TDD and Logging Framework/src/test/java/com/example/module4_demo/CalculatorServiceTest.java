package com.example.module4_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculatorServiceTest {

    @Test
    public void testAddNumbers() {
       
        CalculatorService calculator = new CalculatorService();

        
        int result = calculator.add(10, 5);

        
        assertEquals(15, result);
    }
}