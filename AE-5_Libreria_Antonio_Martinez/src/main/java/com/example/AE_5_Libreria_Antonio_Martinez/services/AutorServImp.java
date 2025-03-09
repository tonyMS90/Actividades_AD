package com.example.AE_5_Libreria_Antonio_Martinez.services;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Autor;
import com.example.AE_5_Libreria_Antonio_Martinez.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServImp implements AutorService{

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public Autor addAutor(Autor autor) {
        return autorRepository.save(autor);
    }

    @Override
    public List<Autor> getAllAutores() {
        return autorRepository.findAll();
    }
}
