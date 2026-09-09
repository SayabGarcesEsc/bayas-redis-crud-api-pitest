package com.redis_repo.crud.service;

import com.redis_repo.crud.entity.Customer;
import com.redis_repo.crud.repository.CustomerRepository;
import com.redis_repo.crud.entity.Book;
import com.redis_repo.crud.repository.BookRepository;
import com.redis_repo.crud.entity.Sharing;
import com.redis_repo.crud.repository.SharingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
//import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class LibraryService {
    private final CustomerRepository customerRepo;
    private final BookRepository bookRepo;
    private final SharingRepository sharingRepo;
    public Sharing shareBook(String customerId, String bookId) {
        Customer customer = customerRepo.findById(customerId).orElseThrow();
        Book book = bookRepo.findById(bookId).orElseThrow();
        Sharing sharing = new Sharing();
        sharing.setId(UUID.randomUUID().toString());
        sharing.setCustomerId(customer.getId());
        sharing.setBookId(book.getId());
        sharing.setStatus("ACTIVE");
        return sharingRepo.save(sharing);
    }
    public Sharing borrowBook(String customerId, String bookId) {
        boolean customerExists = customerRepo.existsById(customerId);
        boolean bookExists = bookRepo.existsById(bookId);
        if (!customerExists || !bookExists) {
            throw new IllegalArgumentException("Invalid Customer ID or Book ID");
        }
        Sharing sharing = new Sharing();
        sharing.setId(UUID.randomUUID().toString());
        sharing.setCustomerId(customerId);
        sharing.setBookId(bookId);
        sharing.setStatus("BORROWED");

        return sharingRepo.save(sharing);
    }
}