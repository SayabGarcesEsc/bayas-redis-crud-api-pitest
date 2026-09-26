package com.redis_repo.crud.controller;

import com.redis_repo.crud.entity.Customer;
import com.redis_repo.crud.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTests {
    @Mock
    private CustomerService service;
    @InjectMocks
    private CustomerController controller;
    @Test
    void createTest() {
        Customer requestCustomer = new Customer();
        Customer savedCustomer = new Customer();
        when(service.addCustomer(requestCustomer)).thenReturn(savedCustomer);
        Customer result = controller.create(requestCustomer);
        assertSame(savedCustomer, result);
        verify(service).addCustomer(requestCustomer);
    }
}