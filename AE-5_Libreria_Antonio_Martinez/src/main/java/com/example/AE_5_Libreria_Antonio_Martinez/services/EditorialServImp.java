package com.example.AE_5_Libreria_Antonio_Martinez.services;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Editorial;
import com.example.AE_5_Libreria_Antonio_Martinez.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EditorialServImp implements EditorialService{

    @Autowired
    private EditorialRepository editorialRepository;

    @Override
    public Editorial addEditorial(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    @Override
    public List<Editorial> getAllEditoriales() {
        return editorialRepository.findAll();
    }
}
