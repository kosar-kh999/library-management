package com.example.demo.controller;

import com.example.demo.data.dto.*;
import com.example.demo.data.model.*;
import com.example.demo.service.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;
    private final ModelMapper modelMapper;

    public PersonController(PersonService personService, ModelMapper modelMapper) {
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add_person")
    public ResponseEntity<PersonDto> addUser(@Valid @RequestBody PersonSignUpDto personSignUpDto) {
        Person person = modelMapper.map(personSignUpDto, Person.class);
        personService.SignUp(person);
        return ResponseEntity.created(null).build();
    }

    @PostMapping("/add_publisher")
    public JsonMessage addPublisher(@Valid @RequestBody PublisherDto publisherDto) {
        Publisher publisher = modelMapper.map(publisherDto, Publisher.class);
        personService.addPublisher(publisher);
        return new JsonMessage(publisherDto.getName() + " added successfully");
    }

    @PostMapping("/add_author")
    public JsonMessage addAuthor(@Valid @RequestBody AuthorDto authorDto) {
        Author author = modelMapper.map(authorDto, Author.class);
        personService.addAuthor(author);
        return new JsonMessage(authorDto.getName() + " added successfully");
    }

    @PostMapping("/add_book")
    public ResponseEntity<BookDto> addBook(@Valid @RequestBody BookDto bookDto,
                                           @RequestParam(value = "idAuthor") Long idAuthor,
                                           @RequestParam(value = "idPublisher") Long idPublisher) {
        Book book = modelMapper.map(bookDto, Book.class);
        Book book1 = personService.addAuthorAndPublisherToBook(idAuthor, idPublisher, book);
        BookDto dto = modelMapper.map(book1, BookDto.class);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/show_books")
    public ResponseEntity<List<BookNameDto>> getAllBooks() {
        return ResponseEntity.ok().body(personService.showAllBooks().stream().map(personService -> modelMapper.map
                (personService, BookNameDto.class)).collect(Collectors.toList()));
    }
}
