package com.redis_repo.crud.service;

import com.redis_repo.crud.entity.Customer;
import com.redis_repo.crud.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repo;
    public Customer addCustomer(Customer c) { 
        return repo.save(c); 
    }
    public Iterable<Customer> getAll() { 
        return repo.findAll(); 
    }
    public Optional<Customer> getById(String id) { 
        return repo.findById(id); 
    }
    public Customer update(String id, Customer c) {
        return repo.findById(id).map(existing -> {
            existing.setName(c.getName());
            existing.setPhone(c.getPhone());
            existing.setEmail(c.getEmail());
            return repo.save(existing);
        }).orElse(null);
    }
    public void delete(String id) { 
        repo.deleteById(id); 
    }
}