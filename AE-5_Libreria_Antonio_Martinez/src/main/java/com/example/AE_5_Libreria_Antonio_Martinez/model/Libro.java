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
@Table(name = "libros")

public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String titulo;

    @Column
    private double precio;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "editorial_id")
    private Editorial editorial;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToMany(mappedBy = "libros")
    private List<Libreria> librerias;

    //CONSTRUCTOR SIN ID


    public Libro(String titulo, double precio, Editorial editorial, Autor autor) {
        this.titulo = titulo;
        this.precio = precio;
        this.editorial = editorial;
        this.autor = autor;
    }

    public Libro(String titulo, double precio) {
        this.titulo = titulo;
        this.precio = precio;
    }
}

