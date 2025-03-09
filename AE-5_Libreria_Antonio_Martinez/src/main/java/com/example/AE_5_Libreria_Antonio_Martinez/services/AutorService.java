package com.example.AE_5_Libreria_Antonio_Martinez.services;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Autor;

import java.util.List;

public interface AutorService {
    Autor addAutor(Autor autor);
    List<Autor> getAllAutores();
}
