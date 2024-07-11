package com.alura.literatura.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Libros, Long> {
    List<Libros> findByTitulo(String titulo);
    List<Libros> findByIdioma(String idioma);
    List<Libros> findByAutores_FechaNacimientoBefore(LocalDate date);

    List<Libros> findByIdiomaContainsIgnoreCase(String idioma);
    Optional<Libros> findByTituloContainsIgnoreCase(String titulo);
}
