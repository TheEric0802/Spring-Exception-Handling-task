package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest @AutoConfigureMockMvc
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCarBrand_shouldReturnPorsche_whenCalledWithPorsche() throws Exception {
        mockMvc.perform(get("/api/cars/porsche"))
                .andExpect(status().isOk())
                .andExpect(content().string("porsche"));
    }

    @Test
    void getCarBrand_shouldReturnBadRequest_whenNotCalledWithPorsche() throws Exception {
        mockMvc.perform(get("/api/cars/bmw"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                        {
                            "message": "Only 'porsche' allowed"
                        }
                    """));
    }

    @Test
    void getAllCars_shouldReturnNotFound_whenCalled() throws Exception {
        mockMvc.perform(get("/api/cars"))
                .andExpect(status().isNotFound())
                .andExpect(content().json("""
                        {
                            "message": "No Cars found"
                        }
                    """));
    }
}