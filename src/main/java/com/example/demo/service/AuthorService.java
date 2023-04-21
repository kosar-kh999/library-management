package com.example.demo.service;

import com.example.demo.data.model.Author;

public interface AuthorService {
    void addAuthor(Author author);

    Author getAuthorById(Long id);
}
