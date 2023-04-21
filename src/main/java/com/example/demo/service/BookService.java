package com.example.demo.service;

import com.example.demo.data.model.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    Book getBookById(Long id);

    List<Book> getAllBooks();
}
