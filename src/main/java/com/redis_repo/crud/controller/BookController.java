package com.redis_repo.crud.controller;

import com.redis_repo.crud.entity.Book;
import com.redis_repo.crud.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;
    @PostMapping public Book create(@RequestBody Book b) { return service.addBook(b); }
    @GetMapping public Iterable<Book> all() { return service.getAll(); }
    @GetMapping("/{id}") public Optional<Book> one(@PathVariable String id) { return service.getById(id); }
    @PutMapping("/{id}") public Book update(@PathVariable String id, @RequestBody Book b) { return service.update(id, b); }
    @DeleteMapping("/{id}") public void delete(@PathVariable String id) { service.delete(id); }
}