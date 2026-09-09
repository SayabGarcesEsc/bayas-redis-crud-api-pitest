package com.redis_repo.crud.controller;

import com.redis_repo.crud.dto.BorrowRequest;
import com.redis_repo.crud.service.LibraryService;
import com.redis_repo.crud.entity.Sharing;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sharings")
@RequiredArgsConstructor
public class SharingController {
    private final LibraryService libraryService;
    @PostMapping("/borrow") public ResponseEntity<?> borrowBook(@RequestBody BorrowRequest request) {
        try {
            Sharing result = libraryService.borrowBook(
                request.getCustomerId(),
                request.getBookId()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error ocurred");
        }
    }
}