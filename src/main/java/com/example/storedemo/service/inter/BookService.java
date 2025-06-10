package com.example.storedemo.service.inter;

import com.example.storedemo.model.dto.BookDto;
import com.example.storedemo.model.dto.request.BookRequest;
import com.example.storedemo.model.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface BookService {

    Page<Book> getAllBooks(Pageable pageable);
    void addBook(BookDto bookDto);
    Book getById(Long id);
    Book updateBook(BookRequest bookRequest);
    Book deleteBook(Long id);

}
