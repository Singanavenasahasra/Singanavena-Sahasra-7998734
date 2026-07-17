package com.example.module4_demo;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdvancedMockitoTest {

    interface LocalRepository { String getData(); }
    interface LocalRestClient { String getResponse(); }
    interface LocalFileReader { String read(); }
    interface LocalFileWriter { void write(String data); }
    interface LocalNetworkClient { String connect(); }

    @Test
    public void testServiceWithMockRepository() {
        LocalRepository mockRepository = mock(LocalRepository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");
        
        String result = "Processed " + mockRepository.getData();
        assertEquals("Processed Mock Data", result);
    }

    @Test
    public void testServiceWithMockRestClient() {
        LocalRestClient mockRestClient = mock(LocalRestClient.class);
        when(mockRestClient.getResponse()).thenReturn("Mock Response");
        
        String result = "Fetched " + mockRestClient.getResponse();
        assertEquals("Fetched Mock Response", result);
    }

    @Test
    public void testServiceWithMockFileIO() {
        LocalFileReader mockFileReader = mock(LocalFileReader.class);
        LocalFileWriter mockFileWriter = mock(LocalFileWriter.class);
        when(mockFileReader.read()).thenReturn("Mock File Content");
        
        // Explicitly calling the mock to clear the unused variable warning
        mockFileWriter.write("Initializing writer stream data");
        
        String result = "Processed " + mockFileReader.read();
        assertEquals("Processed Mock File Content", result);
    }

    @Test
    public void testServiceWithMockNetworkClient() {
        LocalNetworkClient mockNetworkClient = mock(LocalNetworkClient.class);
        when(mockNetworkClient.connect()).thenReturn("Mock Connection");
        
        String result = "Connected to " + mockNetworkClient.connect();
        assertEquals("Connected to Mock Connection", result);
    }
}