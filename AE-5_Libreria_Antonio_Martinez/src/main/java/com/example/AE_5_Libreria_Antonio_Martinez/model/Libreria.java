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
@Table(name = "librerias")

public class Libreria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private String dueño;

    @Column
    private String direccion;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "librerias_libros",
            joinColumns = @JoinColumn(name = "libreria_id"),
            inverseJoinColumns = @JoinColumn(name = "libro_id")
    )
    private List<Libro> libros;


    //CONSTRUCTOR SIN ID


    public Libreria(String nombre, String dueño, String direccion, List<Libro> libros) {
        this.nombre = nombre;
        this.dueño = dueño;
        this.direccion = direccion;
        this.libros = libros;
    }

    public Libreria(List<Libro> libros) {
        this.libros = libros;
    }

    public Libreria(String nombre, String dueño, String direccion) {
        this.nombre = nombre;
        this.dueño = dueño;
        this.direccion = direccion;
    }
}
