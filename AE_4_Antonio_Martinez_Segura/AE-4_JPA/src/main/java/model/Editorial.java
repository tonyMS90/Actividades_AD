package model;

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
@Table(name = "editoriales")

public class Editorial implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nombre;

    @Column
    private String direccion;

    @OneToMany(mappedBy = "editorial", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    //CONSTRUCTOR SIN ID


    public Editorial(String nombre, String direccion, List<Libro> libros) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.libros = libros;
    }

    public Editorial(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }
}
