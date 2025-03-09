package com.example.AE_5_Libreria_Antonio_Martinez.controller;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Libro;
import com.example.AE_5_Libreria_Antonio_Martinez.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping("/addLibro")

    public ResponseEntity<Libro> addLibro(@RequestBody Libro libro){
        return new ResponseEntity<>(libroService.addLibro(libro), HttpStatus.OK);
    }

    @GetMapping("/getAllLibros")
    public ResponseEntity<List<Libro>> getAllLibros(){
        List<Libro> lista = libroService.getAllLibros();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/getLibroByLibreria")
    public ResponseEntity<List<Libro>> getLibroByLibreria(@RequestParam  int libreria_id){
        return new ResponseEntity<>(libroService.getLibrosByLibreria(libreria_id), HttpStatus.OK);
    }
}
