package com.example.demo.service.impl;

import com.example.demo.data.enums.Role;
import com.example.demo.data.model.Author;
import com.example.demo.data.model.Book;
import com.example.demo.data.model.Person;
import com.example.demo.data.model.Publisher;
import com.example.demo.data.repository.PersonRepository;
import com.example.demo.service.AuthorService;
import com.example.demo.service.BookService;
import com.example.demo.service.PersonService;
import com.example.demo.service.PublisherService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final AuthorService authorService;
    private final BookService bookService;
    private final PublisherService publisherService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PersonServiceImpl(PersonRepository personRepository, AuthorService authorService,
                             BookService bookService, PublisherService publisherService,
                             BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.personRepository = personRepository;
        this.authorService = authorService;
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void SignUp(Person person) {
        person.setRole(Role.ROLE_ADMIN);
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));
        personRepository.save(person);
    }

    @Transactional
    public Book addAuthorAndPublisherToBook(Long authorId, Long publisherId, Book book) {
        Author author = authorService.getAuthorById(authorId);
        Publisher publisher = publisherService.getPublisherById(publisherId);
        book.setAuthor(author);
        book.setPublisher(publisher);
        bookService.addBook(book);
        return book;
    }

    public List<Book> showAllBooks() {
        return bookService.getAllBooks();
    }

    public void addAuthor(Author author) {
        authorService.addAuthor(author);
    }

    public void addPublisher(Publisher publisher) {
        publisherService.addPublisher(publisher);
    }

}
