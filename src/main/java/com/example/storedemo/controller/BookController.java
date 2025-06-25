package com.example.storedemo.controller;

import com.example.storedemo.model.dto.BookDto;
import com.example.storedemo.model.dto.request.BookRequest;
import com.example.storedemo.model.entity.Book;
import com.example.storedemo.service.inter.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping("/book/controller")
public class BookController {
    private final BookService bookService;
    private static final Logger logger = LogManager.getLogger(BookController.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable){
        logger.info("this is logger in /getAllBooks endpoint");
        Page<Book> books=bookService.getAllBooks(pageable);
        return ResponseEntity.ok(books);
    }

    @PostMapping("/addBook")
    public ResponseEntity addBook(BookDto bookDto){
        logger.info("this is logger in /getAllBooks endpoint");
        bookService.addBook(bookDto);
         return ResponseEntity.status(201).body(bookDto);
    }

    @GetMapping("/getBookById")
    public ResponseEntity<Book> getBookById(@RequestParam long id){
        logger.info("getBookId worked");
        Book book=null;
        try {
             book=bookService.getById(id);
        }catch(NoSuchElementException ex){
            logger.error("there is no such element in database");
        }
           logger.info("getBookId continues");
        logger.error("this is test error log for getBookId");
           return ResponseEntity.status(200).body(book);
    }
    @PatchMapping("/updateBook")
    public ResponseEntity<String> updateBook(BookRequest bookRequest){
        try {
            Book book = bookService.updateBook(bookRequest);
            return ResponseEntity.status(250).body(book.toString());
        }catch (Exception ex){
            return ResponseEntity.status(400).body("there is no such element in base");
        }
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Book> deleteBook(@RequestParam long id){
        Book book=bookService.deleteBook(id);
        return ResponseEntity.status(300).body(book);
    }
}