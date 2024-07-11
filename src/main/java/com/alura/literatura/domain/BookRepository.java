package com.alura.literatura.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByLanguage(String language);
    List<Book> findByAuthorBirthDateBefore(LocalDate date);
}
