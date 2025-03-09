package com.example.AE_5_Libreria_Antonio_Martinez.repository;


import com.example.AE_5_Libreria_Antonio_Martinez.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
    List<Libro> libroByLibreriaId (int libreria_id);
}
