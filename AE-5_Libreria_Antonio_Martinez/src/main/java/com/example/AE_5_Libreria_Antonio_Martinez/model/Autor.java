package com.example.AE_5_Libreria_Antonio_Martinez.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "autores")
public class Autor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellidos;

    @Column(name = "fecha_nac")
    private String fechaNac;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;


    //CONSTRUCTOR SIN ID


    public Autor(String nombre, String apellidos, String fechaNac, List<Libro> libros) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.libros = libros;
    }

    public Autor(String nombre, String apellidos, String fechaNac) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
    }
}





