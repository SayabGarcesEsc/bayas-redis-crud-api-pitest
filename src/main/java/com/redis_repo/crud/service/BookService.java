package com.redis_repo.crud.service;

import com.redis_repo.crud.entity.Book;
import com.redis_repo.crud.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repo;
    public Book addBook(Book b) { return repo.save(b); }
    public Iterable<Book> getAll() { return repo.findAll(); }
    public Optional<Book> getById(String id) { return repo.findById(id); }
    public Book update(String id, Book b) {
        return repo.findById(id).map(existing -> {
            existing.setTitle(b.getTitle());
            existing.setAuthor(b.getAuthor());
            existing.setIsbn(b.getIsbn());
            existing.setPrice(b.getPrice());
            existing.setPublishedYear(b.getPublishedYear());
            return repo.save(existing);
        }).orElse(null);
    }
    public void delete(String id) { repo.deleteById(id); }
}