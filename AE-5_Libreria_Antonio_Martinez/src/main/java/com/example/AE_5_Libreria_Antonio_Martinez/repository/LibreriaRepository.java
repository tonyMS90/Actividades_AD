package com.example.AE_5_Libreria_Antonio_Martinez.repository;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Autor;
import com.example.AE_5_Libreria_Antonio_Martinez.model.Libreria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibreriaRepository extends JpaRepository<Libreria, Integer> {
}
