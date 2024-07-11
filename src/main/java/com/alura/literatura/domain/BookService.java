package com.alura.literatura.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<String> findAllAuthors() {
        return bookRepository.findAll().stream().map(Book::getAuthor).distinct().toList();
    }

    public List<Book> findBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public List<Book> findAuthorsAliveInYear(int year) {
        LocalDate date = LocalDate.of(year, 12, 31);
        return bookRepository.findByAuthorBirthDateBefore(date);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
