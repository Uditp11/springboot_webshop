package com.ecomweb.online.da.DA;

import com.ecomweb.online.da.DA.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityAndDeleteTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saasCatalogRejectsMissingOrWrongKey() throws Exception {
        mockMvc.perform(get("/saas/catalog")).andExpect(status().isUnauthorized());
        mockMvc.perform(get("/saas/catalog").header("X-API-KEY", "wrong")).andExpect(status().isUnauthorized());
    }

    @Test
    void saasCatalogAcceptsConfiguredKey() throws Exception {
        mockMvc.perform(get("/saas/catalog").header("X-API-KEY", "Uditp_11")).andExpect(status().isOk());
    }

    @Test
    void corsPreflightFromSvelteClientIsAllowed() throws Exception {
        mockMvc.perform(options("/saas/catalog")
                        .header("Origin", "http://localhost:5173")
                        .header("Access-Control-Request-Method", "GET")
                        .header("Access-Control-Request-Headers", "X-API-KEY"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteDoesNotRunOnGet() throws Exception {
        mockMvc.perform(get("/product-delete/1")).andExpect(status().isMethodNotAllowed());
        assertTrue(productRepository.existsById(1L));
    }

    @Test
    void deleteRunsOnPost() throws Exception {
        mockMvc.perform(post("/product-delete/2")).andExpect(status().is3xxRedirection());
        assertFalse(productRepository.existsById(2L));
    }
}
