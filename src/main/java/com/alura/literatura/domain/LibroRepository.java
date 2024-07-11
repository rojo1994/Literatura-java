package com.alura.literatura.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libros, Long> {


    Optional<Libros> findByTituloContainsIgnoreCase(String titulo);

    List<Libros> findByIdiomaContainsIgnoreCase(String idioma);
}
