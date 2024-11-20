package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

//Clase modelo con los atributos de clase, getter, setter, constructores y toString

public class Coche implements Serializable{

        private final static long serialVersionUID = 123456789L;
        private int id;
        private String matricula;
        private String marca;
        private String modelo;
        private String color;

        @Override
        public String toString() {
                return "Coche{" +
                        "id=" + id +
                        ", matricula='" + matricula + '\'' +
                        ", marca='" + marca + '\'' +
                        ", modelo='" + modelo + '\'' +
                        ", color='" + color + '\'' +
                        '}';
        }
}