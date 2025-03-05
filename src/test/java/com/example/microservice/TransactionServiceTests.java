package com.example.microservice.controller;

import com.example.microservice.model.Transaction;
import com.example.microservice.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        transactionRepository.deleteAll();
    }

    @Test
    void testCreateTransaction() throws Exception {
        Transaction transaction = new Transaction("1", "Test Transaction", 100.50);

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    void testGetTransactionById_NotFound() throws Exception {
        mockMvc.perform(get("/transactions/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllTransactions() throws Exception {
        transactionRepository.save(new Transaction("1", "Test Transaction 1", 100.0));
        transactionRepository.save(new Transaction("2", "Test Transaction 2", 200.0));

        mockMvc.perform(get("/transactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testUpdateTransaction() throws Exception {
        transactionRepository.save(new Transaction("1", "Old Description", 50.0));

        Transaction updatedTransaction = new Transaction("1", "Updated Description", 300.0);

        mockMvc.perform(put("/transactions/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedTransaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.amount").value(300.0));
    }

    @Test
    void testDeleteTransaction() throws Exception {
        transactionRepository.save(new Transaction("1", "Test Transaction", 100.0));

        mockMvc.perform(delete("/transactions/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Transaction deleted successfully"));
    }
}
