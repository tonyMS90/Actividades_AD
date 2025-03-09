package com.example.AE_5_Libreria_Antonio_Martinez.controller;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Autor;
import com.example.AE_5_Libreria_Antonio_Martinez.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @PostMapping("/add")

    public ResponseEntity<Autor> addAutor(@RequestBody Autor autor){
        return new ResponseEntity<>(autorService.addAutor(autor), HttpStatus.OK);
    }

    @GetMapping("/getAllAutores")
    public ResponseEntity<List<Autor>> getAllAutores(){
        List<Autor> lista = autorService.getAllAutores();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
