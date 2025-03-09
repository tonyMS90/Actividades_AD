package com.example.AE_5_Libreria_Antonio_Martinez.controller;


import com.example.AE_5_Libreria_Antonio_Martinez.model.Autor;
import com.example.AE_5_Libreria_Antonio_Martinez.model.Editorial;
import com.example.AE_5_Libreria_Antonio_Martinez.services.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @PostMapping("/addEditorial")

    public ResponseEntity<Editorial> addEditorial(@RequestBody Editorial editorial){
        return new ResponseEntity<>(editorialService.addEditorial(editorial), HttpStatus.OK);
    }

    @GetMapping("/getAllEditoriales")
    public ResponseEntity<List<Editorial>> getAllAEditoriales(){
        List<Editorial> lista = editorialService.getAllEditoriales();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
