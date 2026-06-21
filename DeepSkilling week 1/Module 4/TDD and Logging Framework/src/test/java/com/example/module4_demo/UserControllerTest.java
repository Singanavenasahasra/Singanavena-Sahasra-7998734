package com.example.module4_demo;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@SuppressWarnings("all") 
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetEndpointSuccess() throws Exception {
        User user = new User(495L, "R Bhavana");
        when(userService.getUserById(495L)).thenReturn(user);

        mockMvc.perform(get("/users/495"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("R Bhavana"));
    }

    @Test
    void testPostEndpointExecution() throws Exception {
        User user = new User(495L, "R Bhavana");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":495,\"name\":\"R Bhavana\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(495));
    }

    @Test
    void testControllerAdviceExceptionHandler() throws Exception {
        when(userService.getUserById(999L)).thenThrow(new NoSuchElementException("User not found"));

        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}