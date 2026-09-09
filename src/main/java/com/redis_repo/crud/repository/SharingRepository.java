package com.redis_repo.crud.repository;

import com.redis_repo.crud.entity.Sharing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SharingRepository extends CrudRepository<Sharing,String> {
    List<Sharing> findByCustomerId(String customerId);
    List<Sharing> findByBookId(String bookId);
}