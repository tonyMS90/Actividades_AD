package com.example.AE_5_Libreria_Antonio_Martinez.services;

import com.example.AE_5_Libreria_Antonio_Martinez.model.Editorial;

import java.util.List;

public interface EditorialService {
    Editorial addEditorial(Editorial editorial);
    List<Editorial> getAllEditoriales();
}
