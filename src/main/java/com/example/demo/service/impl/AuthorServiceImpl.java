package com.example.demo.service.impl;

import com.example.demo.data.model.Author;
import com.example.demo.data.repository.AuthorRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.util.exception.NotFoundAuthor;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(() -> new NotFoundAuthor("This author is not found"));
    }
}
