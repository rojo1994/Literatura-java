package com.alura.literatura.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String idioma;
    private Double numeroDescargas;

    @OneToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autores> autores;

    public Libros() {}

    public Libros(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.idioma = datos.idioma().get(0);
        this.numeroDescargas = datos.numeroDescargas();
    }

    // Getters y setters...

    @Override
    public String toString() {
        return "Libro:" + "\n" +
                "Titulo=" + titulo + "\n" +
                "Idioma=" + idioma + "\n" +
                "Numero de Descargas=" + numeroDescargas +
                "\n";
    }
}
