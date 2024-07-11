package com.alura.literatura.domain;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Optional<Libros> libroBuscado;
    private BookRepository repositorio;

    public Main(BookRepository repository) {
        this.repositorio = repository;
    }

    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu= """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar libros por idioma
                    
                    4 - Guardar un libro en la base de Datos
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarLibrosPorIdioma();
                    break;
                case 4:
                    buscarLibroWeb();
                    break;
                case 0:
                    System.out.println("Cerrando aplicacion");
                    break;
                default:
                    System.out.println("Opcion Invalida, seleccione una opcion correcta");
            }
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Escribe el nombre del idioma del libro (en / es): ");
        var idiomaLibro = teclado.nextLine();
        List<Libros> librosPorIdioma = repositorio.findByIdiomaContainsIgnoreCase(idiomaLibro);
        if (librosPorIdioma.isEmpty()){
            System.out.println("No se encontraron libros en ese idioma. Escribe otro idioma.");
        } else {
            System.out.println("Los libros buscados son:");
            librosPorIdioma.forEach(System.out::println);
        }
    }

    private void listarLibros() {
        List<Libros> librosListados = repositorio.findAll();
        librosListados.forEach(System.out::println);
    }

    private void buscarLibroWeb() {
        DatosLibro datos = getDatosLibro();
        if (datos != null) {
            Libros libros = new Libros(datos);
            try {
                repositorio.save(libros);
                System.out.println("Libro guardado: " + libros);
            } catch (Exception e) {
                System.err.println("Error al guardar el libro: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No se pudo encontrar el libro.");
        }
    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        System.out.println(json);
        DatosDelLibro datos = convierteDatos.obtenerDatos(json, DatosDelLibro.class);

        if (datos != null && datos.libros() != null && !datos.libros().isEmpty()) {
            DatosLibro primerLibro = datos.libros().get(0);

            String idioma = primerLibro.idioma() != null && !primerLibro.idioma().isEmpty() ? primerLibro.idioma().get(0) : "Desconocido";

            return new DatosLibro(
                    primerLibro.titulo(),
                    List.of(idioma),
                    primerLibro.numeroDescargas());
        } else {
            System.out.println("No se encontraron libros con ese título.");
            return null;
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Escribe el titulo del libro a buscar: ");
        var tituloLibro = teclado.nextLine();

        libroBuscado = repositorio.findByTituloContainsIgnoreCase(tituloLibro);
        if (libroBuscado.isPresent()) {
            System.out.println("El libro buscado es:");
            System.out.println(libroBuscado.get());
        } else {
            System.out.println("No se encontró el libro. Escribe otro título.");
        }
    }
}
