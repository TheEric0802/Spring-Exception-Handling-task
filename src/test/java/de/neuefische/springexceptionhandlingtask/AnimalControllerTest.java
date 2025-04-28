package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest @AutoConfigureMockMvc
class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAnimalSpecies_shouldReturnDog_whenCalledWithDog() throws Exception {
        mockMvc.perform(get("/api/animals/dog"))
                .andExpect(status().isOk())
                .andExpect(content().string("dog"));
    }

    @Test
    void getAnimalSpecies_shouldReturnBadRequest_whenNotCalledWithDog() throws Exception {
        mockMvc.perform(get("/api/animals/cat"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        {
                            "message": "Only 'dog' is allowed"
                        }
                    """));
    }

    @Test
    void getAllAnimals_shouldReturnNotFound_whenCalled() throws Exception {
        mockMvc.perform(get("/api/animals"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                        {
                            "message": "No Animals found"
                        }
                    """));
    }
}