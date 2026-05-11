package com.Elansari.Mohamed.Reda.demo;

import com.Elansari.Mohamed.Reda.demo.dtos.ClientDTO;
import com.Elansari.Mohamed.Reda.demo.repositories.ClientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        clientRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateClientValidationFailure() throws Exception {
        ClientDTO invalidClient = new ClientDTO(null, "", "invalid-email");

        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidClient)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.nom").exists())
                .andExpect(jsonPath("$.email").exists());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testGetClientNotFound() throws Exception {
        mockMvc.perform(get("/api/clients/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Client non trouvé avec l'ID: 999"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testCreateClientForbidden() throws Exception {
        ClientDTO client = new ClientDTO(null, "John", "john@test.com");
        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isForbidden());
    }
}
