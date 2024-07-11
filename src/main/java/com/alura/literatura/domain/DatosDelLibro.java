package com.alura.literatura.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosDelLibro(
        @JsonAlias("results") List<DatosLibro> libros
) {

}
