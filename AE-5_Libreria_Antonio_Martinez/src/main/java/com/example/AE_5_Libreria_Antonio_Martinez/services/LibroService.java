package com.example.AE_5_Libreria_Antonio_Martinez.services;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Libro;

import java.util.List;

public interface LibroService {
    Libro addLibro (Libro libro);
    List<Libro> getAllLibros();
    List<Libro> getLibrosByLibreria(int libreria_id);
}
