package com.redis_repo.crud.service;

import com.redis_repo.crud.entity.Customer;
import com.redis_repo.crud.repository.CustomerRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTests {
    @Mock
    private CustomerRepository repo;
    @InjectMocks
    private CustomerService service;
    @Test
    void addCustomerTest() {
        // Arrange
        Customer customer = new Customer("1", "Customer Name", 1234567890L, "customer@library.com");
        when(repo.save(any(Customer.class))).thenReturn(customer);
        // Act
        Customer added = service.addCustomer(customer);
        // Assert
        assertNotNull(added);
        assertEquals("1", added.getId());
        assertEquals("Customer Name", added.getName());
        assertEquals(1234567890L, added.getPhone());
        assertEquals("customer@library.com", added.getEmail());
        verify(repo, times(1)).save(customer);
    }
}