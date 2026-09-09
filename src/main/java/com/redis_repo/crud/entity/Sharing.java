package com.redis_repo.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Sharing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sharing implements Serializable {
    @Id
    private String id;
    private String customerId;
    private String bookId;
    private String status;
}