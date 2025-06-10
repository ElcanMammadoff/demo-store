package com.example.storedemo.repository;

import com.example.storedemo.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Jpa21Utils;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Page<Book> findAll(Pageable pageable);
    Optional<Book> findById(Long id);

}
