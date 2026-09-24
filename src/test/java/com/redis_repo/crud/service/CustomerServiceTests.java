package com.redis_repo.crud.service;

import com.redis_repo.crud.entity.Customer;
import com.redis_repo.crud.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    @Test
    void getAllTest() {
        // Arrange
        Customer oneCustomer = new Customer("1", "First Customer Name", 1111111111L, "firstCustomer@library.com");
        Customer twoCustomer = new Customer("2", "Second Customer Name", 2222222222L, "secondCustomer@library.com");
        Customer threeCustomer = new Customer("3", "Third Customer Name", 3333333333L, "thirdCustomer@library.com");
        Iterable<Customer> customers = List.of(threeCustomer, twoCustomer);
        when(repo.findAll()).thenReturn(customers);
        // Act
        Iterable<Customer> getAllCustomers = service.getAll();
        // Assert
        assertNotNull(getAllCustomers);
        assertThat(getAllCustomers)
            .isNotEmpty()
            .hasSize(2)
            .contains(twoCustomer)
            .doesNotContain(oneCustomer)
            .startsWith(threeCustomer)
            .endsWith(twoCustomer)
            .containsExactlyInAnyOrder(twoCustomer, threeCustomer);
    }
    @Test
    void getByIdTest() {
         // Arrange
        Customer customer = new Customer("1", "Customer Name", 1234567890L, "customer@library.com");
        Optional<Customer> optionalCustomer = Optional.of(customer);
        when(repo.findById(anyString())).thenReturn(optionalCustomer);
        // Act
        Customer customerFound = service
            .getById(anyString())
            .get();
        // Assert
        assertThat(customerFound)
            .isEqualToComparingFieldByFieldRecursively(optionalCustomer.get());
        verify(repo, times(1)).findById(anyString());
    }
}