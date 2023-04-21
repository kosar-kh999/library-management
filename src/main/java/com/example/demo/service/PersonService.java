package com.example.demo.service;

import com.example.demo.data.model.Author;
import com.example.demo.data.model.Book;
import com.example.demo.data.model.Person;
import com.example.demo.data.model.Publisher;

import java.util.List;

public interface PersonService {
    void SignUp(Person person);

    Book addAuthorAndPublisherToBook(Long authorId, Long bookId, Book book);

    List<Book> showAllBooks();

    void addAuthor(Author author);

    void addPublisher(Publisher publisher);
}
