package com.redis_repo.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.math.BigDecimal;

@RedisHash("Book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {
    @Id
    private String id;
    private String title;
    private String author;
    private String isbn;
    private BigDecimal price;
    private int publishedYear;
}