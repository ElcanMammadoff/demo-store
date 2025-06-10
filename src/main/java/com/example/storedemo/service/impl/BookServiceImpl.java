package com.example.storedemo.service.impl;

import com.example.storedemo.model.dto.BookDto;
import com.example.storedemo.model.dto.request.BookRequest;
import com.example.storedemo.model.entity.Book;
import com.example.storedemo.repository.BookRepository;
import com.example.storedemo.service.inter.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public void addBook(BookDto bookDto) {
        Book book = Book.builder().name(bookDto.getName()).author(bookDto.getAuthor()).build();
        bookRepository.save(book);
    }

    @Override
    public Book getById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.get();
    }

    @Override
    public Book updateBook(BookRequest bookRequest) throws NoSuchElementException{
        try {
            long id = bookRequest.getId();
                Book book = bookRepository.findById(id).get();


            book.setName(bookRequest.getName());
            book.setAuthor(bookRequest.getAuthor());
            return bookRepository.save(book);

        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
            throw e; // or return null / custom error response
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            throw e; // or handle accordingly
        }
    }

    @Override
    public Book deleteBook(Long id) {
        try {
            Book book = getById(id);
            bookRepository.delete(book);
            return book;
        }catch (Exception ex){
            System.out.println("there is a exception");
            throw ex;
        }
    }
}
