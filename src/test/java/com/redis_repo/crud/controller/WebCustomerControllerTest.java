package com.redis_repo.crud.controller;

import com.redis_repo.crud.entity.Customer;
import com.redis_repo.crud.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class) // Focuses only on the web layer for this controller
class WebCustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper(); // Used to serialize Java objects to JSON
    @MockitoBean
    private CustomerService service; // Mocks the service layer dependency
    @Test
    @DisplayName("Should create a customer and return 200 OK with the created customer data")
    void createCustomer_ValidInput_ReturnsCreatedCustomer() throws Exception {
        // Arrange
        Customer inputCustomer = new Customer("null", "Customer Name", 1234567890L, "customer@library.com");
        Customer savedCustomer = new Customer("1", "Customer Name", 1234567890L, "customer@library.com");
        // conditions
        when(service.addCustomer(any(Customer.class))).thenReturn(savedCustomer);
        // Act & Assert
        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputCustomer)))
                .andExpect(status().isOk()) // Or status().isCreated() if you update your controller annotation later
                .andExpect(jsonPath("$.id").value(is(equalTo("1"))))
                .andExpect(jsonPath("$.name").value(is(equalTo("Customer Name"))))
                .andExpect(jsonPath("$.phone").value(is(equalTo(1234567890))))
                .andExpect(jsonPath("$.email").value(is(equalTo("customer@library.com"))));
    }
}