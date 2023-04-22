package com.example.demo.service.impl;

import com.example.demo.data.model.Book;
import com.example.demo.data.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.util.exception.NotFoundBook;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundBook("This book is not found"));
    }

    @Cacheable("book")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

}
