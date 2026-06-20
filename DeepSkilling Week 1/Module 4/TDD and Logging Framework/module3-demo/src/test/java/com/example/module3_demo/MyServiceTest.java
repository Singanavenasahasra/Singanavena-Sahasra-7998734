package com.example.module3_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MyServiceTest {

    // ==========================================
    // EXERCISE 1: Mocking and Stubbing [cite: 79]
    // ==========================================
    @Test
    public void exercise1_MockingAndStubbing() {
        
        ExternalApi mockApi = Mockito.mock(ExternalApi.class); 
        when(mockApi.getData()).thenReturn("Mock Data");       
        MyService service = new MyService(mockApi);            

       
        String result = service.fetchData();                   

       
        assertEquals("Mock Data", result);                     
    }

    // ==========================================
    // EXERCISE 2: Verifying Interactions [cite: 100]
    // ==========================================
    @Test
    public void exercise2_VerifyingInteractions() {
       
        ExternalApi mockApi = Mockito.mock(ExternalApi.class); 
        MyService service = new MyService(mockApi);            

        
        service.fetchData();                                   
        
        verify(mockApi).getData();                             
    }

    // ==========================================
    // EXERCISE 3: Argument Matching [cite: 120]
    // ==========================================
    @Test
    public void exercise3_ArgumentMatching() {
        
        ExternalApi mockApi = Mockito.mock(ExternalApi.class); 
        MyService service = new MyService(mockApi);
        
        when(mockApi.getUserData(anyString())).thenReturn("Profile Data");

        
        String result = service.getUserDetails("student_id_99"); 

       
        assertEquals("Profile Data", result);
        verify(mockApi).getUserData(anyString());              
    }

    // ==========================================
    // EXERCISE 4: Handling Void Methods [cite: 127]
    // ==========================================
    @Test
    public void exercise4_HandlingVoidMethods() {
        
        ExternalApi mockApi = Mockito.mock(ExternalApi.class); 
        MyService service = new MyService(mockApi);
        
        doNothing().when(mockApi).logSession("admin");         

       
        service.recordUserSession("admin");

        
        verify(mockApi).logSession("admin");                   
    }

    // ==========================================
    // EXERCISE 5: Multiple Returns [cite: 134]
    // ==========================================
    @Test
    public void exercise5_MultipleReturnValues() {
      
        ExternalApi mockApi = Mockito.mock(ExternalApi.class); 
        MyService service = new MyService(mockApi);
       
        when(mockApi.getData())
            .thenReturn("First Response")
            .thenReturn("Second Response");                     
       
        String firstCall = service.fetchData();                
        assertEquals("First Response", firstCall);             

        
        String secondCall = service.fetchData();                
        assertEquals("Second Response", secondCall);            
    }

    // ==========================================
    // EXERCISE 6: Verifying Interaction Order [cite: 141]
    // ==========================================
    @Test
    public void exercise6_VerifyingInteractionOrder() {
        
        ExternalApi mockApi = Mockito.mock(ExternalApi.class); 
        MyService service = new MyService(mockApi);

        
        service.getUserDetails("user1");                       
        service.recordUserSession("user1");                    

        
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).getUserData("user1");          
        inOrder.verify(mockApi).logSession("user1");           
    }

    // ==========================================
    // EXERCISE 7: Void Methods with Exceptions [cite: 148]
    // ==========================================
    @Test
    public void exercise7_VoidMethodsWithExceptions() {
        
        ExternalApi mockApi = Mockito.mock(ExternalApi.class); 
        MyService service = new MyService(mockApi);
        
        doThrow(new RuntimeException("Inactivity Timeout"))
            .when(mockApi).logSession("expired_user");         
        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.recordUserSession("expired_user");
        });

        
        assertEquals("Inactivity Timeout", exception.getMessage());

        
        verify(mockApi).logSession("expired_user");             
    }
}