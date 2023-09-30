package com.example.springapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringappApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testAddMedicineApi() throws Exception {
        // Create a JSON representation of the Medicine
        String medicineJson = "{\"id\": 1, \"name\": \"Medicine 1\", \"price\": 10.0, \"quantity\": 5, \"description\": \"Description 1\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(medicineJson))  // Send the JSON representation as content
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

   

    @Test
    public void testUpdateMedicineApi() throws Exception {
        // Create a JSON representation of the updated Medicine
        String updatedMedicineJson = "{\"id\": 1, \"name\": \"Updated Medicine\", \"price\": 20.0, \"quantity\": 10, \"description\": \"Updated Description\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedMedicineJson))  // Send the JSON representation as content
                .andExpect(status().isOk());
    }
}
