package com.example.AE_5_Libreria_Antonio_Martinez.services;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Libreria;
import com.example.AE_5_Libreria_Antonio_Martinez.repository.LibreriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibreriaServImp implements LibreriaService{

    @Autowired
    private LibreriaRepository libreriaRepository;

    @Override
    public Libreria addLibreria(Libreria libreria) {
        return libreriaRepository.save(libreria);
    }

    @Override
    public List<Libreria> getAllLibrerias() {
        return libreriaRepository.findAll();
    }
}
