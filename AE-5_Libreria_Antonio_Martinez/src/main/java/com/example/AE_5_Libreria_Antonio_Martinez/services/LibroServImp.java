package com.example.AE_5_Libreria_Antonio_Martinez.services;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Libro;
import com.example.AE_5_Libreria_Antonio_Martinez.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServImp implements LibroService{


    @Autowired
    private LibroRepository libroRepository;

    @Override
    public Libro addLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public List<Libro> getAllLibros() {
        return libroRepository.findAll();
    }

    @Override
    public List<Libro> getLibrosByLibreria(int libreria_id) {
        return libroRepository.libroByLibreriaId(libreria_id);
    }
}
