package com.example.demo.data.repository;

import com.example.demo.data.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findUserByUsername(String username);

}
