package com.example.AE_5_Libreria_Antonio_Martinez.services;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Libreria;

import java.util.List;

public interface LibreriaService {
    Libreria addLibreria (Libreria libreria);
    List<Libreria> getAllLibrerias();
}
