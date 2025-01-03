package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pasajero {

    private int id;
    private String nombre;
    private int edad;
    private double peso;
    private int idCoche;

    //constructor sin id

    public Pasajero(String nombre, int edad, double peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }

    //Metodo para mostrar los datos del pasajero

    public void mostrarDatos(){
        System.out.println("nombre = " + nombre);
        System.out.println("edad = " + edad);
        System.out.println("idCoche = " + idCoche);
    }
}

