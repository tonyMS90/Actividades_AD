package controller;

import dao.CocheDao;
import lombok.AllArgsConstructor;
import model.Coche;

import java.io.*;
import java.util.ArrayList;

//Clase controladora.
//Se inicializa ArrayList de coches
//Se añade ruta y se asignan excepciones incluyendo una por si la lista está vacia
//se añade metodo guardarCoches para que al finalizar el menu se guarden en coches.dat
//Se implementan los metodos del DAO

public class Concesionario implements CocheDao {

    private static ArrayList<Coche> coches = new ArrayList<>();

    public void leerCoches(){

        File fileCoches = new File("src/main/java/resources/coches.dat");
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        if (fileCoches.exists()&&fileCoches.isFile()){

            try {
                objectInputStream = new ObjectInputStream(new FileInputStream(fileCoches));
                coches = (ArrayList<Coche>) objectInputStream.readObject();
            }catch(EOFException e){
                //excepción que indica que el archivo esta vacio
                System.out.println("Archivo vacio...");

            } catch (IOException e) {
                System.out.println("Error en la lectura");;
            } catch (ClassNotFoundException e) {
                System.out.println("No se puede encontar la clase de mapeo");
                coches = new ArrayList<>();
            }
            finally{
                try {
                    objectInputStream.close();
                } catch (IOException |NullPointerException e) {
                    System.out.println("Error en el cerrado");
                }

            }
        }
        else {
            System.out.println("El fichero coches.dat no existe");
        }
    }

    public void guardarCoches(){
        File fileCoches = new File("src/main/java/resources/coches.dat");
        ObjectOutputStream objectOutputStream = null;
        try{
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileCoches));
            objectOutputStream.writeObject(coches);
            System.out.println("fichero actualizado (coches.dat)");
        }  catch (IOException e) {
            System.out.println("error al guardar los datos: " + e.getMessage());
        }finally {
            try{
                objectOutputStream.close();
            } catch (IOException e) {
                System.out.println("error en el cerrado: " + e.getMessage());
            }
        }
    }


    //METODOS IMPLEMENTADOS
    @Override
    public void añadirCoche(int id, String matricula, String marca, String modelo, String color) {
        // Comprobar que no existe un coche con el mismo id o matrícula
        for (Coche coche : coches) {
            if (coche.getId() == id) {
                System.out.println("El id introducido ya está asociado a un coche.");
                return;
            }
            if (coche.getMatricula().equals(matricula)) {
                System.out.println("La matrícula introducida ya está asociada a un coche.");
                return;
            }
        }
        //añadir coche a lista
        coches.add(new Coche(id, matricula, marca, modelo, color));
        System.out.println("Coche añadido correctamente.");
    }


    @Override
    public void borrarCoche(int id) {
        // Buscar y borrar el coche por id
        for (int i = 0; i < coches.size(); i++) {
            Coche coche = coches.get(i);
            if (coche.getId() == id) {
                coches.remove(i);
                System.out.println("Coche eliminado correctamente.");
                return;
            }
        }
        System.out.println("Coche con el id " + id + " no encontrado.");
    }


    // Buscar coche por id
    @Override
    public Coche consultarCoche(int id) {
        for (Coche coche : coches) {
            if (coche.getId() == id) {
                return coche;
            }
        }
        return null;  // Retorna null si no se encuentra el coche
    }


    // Listar coches
    @Override
    public void listarCoches() {
        if (coches.isEmpty()) {
            System.out.println("No hay coches registrados.");
        } else {
            for (Coche coche : coches) {
                System.out.println(coche);
            }
        }
    }

    //Exportar a archivo CSV
    public void exportarCSV() {
        // Ruta del archivo CSV
        String ruta = "src/main/java/resources/coches.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write("ID;Matrícula;Marca;Modelo;Color");
            writer.newLine();

            for (Coche coche : coches) {
                writer.write(coche.getId() + ";" +
                        coche.getMatricula() + ";" +
                        coche.getMarca() + ";" +
                        coche.getModelo() + ";" +
                        coche.getColor());
                writer.newLine();
            }

            System.out.println("Coches exportados con exito...");
        } catch (IOException e) {
            System.out.println("Error al exportar los coches. " + e.getMessage());
        }
    }
}
