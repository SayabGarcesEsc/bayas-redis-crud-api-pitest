package com.redis_repo.crud.controller;

import com.redis_repo.crud.entity.Customer;
import com.redis_repo.crud.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;
    @PostMapping 
    public Customer create(@RequestBody Customer c) { 
        return service.addCustomer(c); 
    }
    @GetMapping 
    public Iterable<Customer> all() { 
        return service.getAll(); 
    }
    @GetMapping("/{id}") 
    public Optional<Customer> one(@PathVariable String id) { 
        return service.getById(id); 
    }
    @PutMapping("/{id}") 
    public Customer update(@PathVariable String id, @RequestBody Customer c) { 
        return service.update(id, c); 
    }
    @DeleteMapping("/{id}") 
    public void delete(@PathVariable String id) { 
        service.delete(id); 
    }
}