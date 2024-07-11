package com.alura.literatura;

import com.alura.literatura.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    private static final String URL_BASE = "https://gutendex.com/books?search=";

    @Autowired
    private BookRepository libroRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(libroRepository);
        main.mostrarMenu();
    }



}
