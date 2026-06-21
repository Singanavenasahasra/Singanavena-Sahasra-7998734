package com.example.module4_demo;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MyServiceTest {

    interface LocalExternalApi { 
        String getData(); 
        void sendData(String msg); 
    }

    @Test
    public void testExternalApi() {
        LocalExternalApi mockApi = mock(LocalExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");
        
        String result = mockApi.getData();
        assertEquals("Mock Data", result);
    }

    @Test
    public void testVerifyInteraction() {
        LocalExternalApi mockApi = mock(LocalExternalApi.class);
        mockApi.getData();
        
        verify(mockApi).getData();
    }

    @Test
    void testArgumentMatchingAndVoidMethods() {
        LocalExternalApi mockApi = mock(LocalExternalApi.class);
        mockApi.sendData("Hello Platform");
        
        verify(mockApi).sendData(eq("Hello Platform"));
    }

    @Test
    void testConsecutiveReturnsAndOrder() {
        LocalExternalApi mockApi = mock(LocalExternalApi.class);
        when(mockApi.getData()).thenReturn("First").thenReturn("Second");
        
        assertEquals("First", mockApi.getData());
        assertEquals("Second", mockApi.getData());
        
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi, times(2)).getData();
    }

    @Test
    void testVoidMethodException() {
        LocalExternalApi mockApi = mock(LocalExternalApi.class);
        doThrow(new RuntimeException("Error")).when(mockApi).sendData(anyString());
        
        // Assert result captured into a local variable to clear the ignored output warning
        Throwable throwable = assertThrows(RuntimeException.class, () -> mockApi.sendData("Crash"));
        assertNotNull(throwable.getMessage());
    }
}