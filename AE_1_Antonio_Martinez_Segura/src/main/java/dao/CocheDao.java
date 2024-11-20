package dao;

import model.Coche;

//Interface con los metodos que se implementarán en la clase concesionario

public interface CocheDao {
    //Añadir coche
     void añadirCoche(int id, String matricula, String marca, String modelo, String color);

    // Borrar coche por ID
    void borrarCoche(int id);

    // Consultar coche por ID
    Coche consultarCoche(int id);

    // Dar una lista de coches
    void listarCoches();

}
