package com.example.AE_5_Libreria_Antonio_Martinez.controller;


import com.example.AE_5_Libreria_Antonio_Martinez.model.Autor;
import com.example.AE_5_Libreria_Antonio_Martinez.model.Libreria;
import com.example.AE_5_Libreria_Antonio_Martinez.services.LibreriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libreria")
public class LibreriaController {

    @Autowired
    private LibreriaService libreriaService;

    @PostMapping("/addLibreria")

    public ResponseEntity<Libreria> addLibreria(@RequestBody Libreria libreria){
        return new ResponseEntity<>(libreriaService.addLibreria(libreria), HttpStatus.OK);
    }

    @GetMapping("/getAllAutores")
    public ResponseEntity<List<Libreria>> getAllLibrerias(){
        List<Libreria> lista = libreriaService.getAllLibrerias();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
