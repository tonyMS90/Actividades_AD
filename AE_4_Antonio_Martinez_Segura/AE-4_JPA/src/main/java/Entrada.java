import controller.Controlador;
import model.Autor;
import model.Editorial;
import model.Libreria;
import model.Libro;

import java.util.ArrayList;
import java.util.List;

public class Entrada {
    public static void main(String[] args) {

        Controlador controlador = new Controlador();


        //Editorial
        //controlador.crearEditorial(new Editorial("ANAYA", "Calle la Luna"));
        //controlador.crearEditorial(new Editorial("PLANETA", "Calle los Astros"));

        //Autor
        //controlador.crearAutor(new Autor("Elisabet", "Benavent", "12/08/1987"));
        //controlador.crearAutor(new Autor("Bryan", "Weis", "15/06/1975"));
        //controlador.crearAutor(new Autor("Luis", "Perez", "18/01/1990"));

        //Libros
        controlador.crearLibro(new Libro("Mi isla", 22.90),1, 1);
        controlador.crearLibro(new Libro("Muchas vidas, muchos Maestros", 19.90),2, 2);
        controlador.crearLibro(new Libro("La Rueda", 21.20),3, 1);
        controlador.crearLibro(new Libro("La Noche", 25.99),1, 2);
        controlador.crearLibro(new Libro("Siempre a tu lado", 20.90),1, 1);
        controlador.crearLibro(new Libro("Amanecer", 17.99),2, 2);
        controlador.crearLibro(new Libro("Noche oscura", 12.90),3, 1);
        controlador.crearLibro(new Libro("Siete vidas", 18.90),1, 2);



        //crear librarias y asignar libros

       Libreria libreria1 = new Libreria("La casa del libro", "Tomás Martinez", "Avenida Perez Galdos");
       Libreria libreria2 = new Libreria("Librería Central", "Ana Martínez", "Calle Mayor");

      //obtener los libros creados
        List<Libro> libros = controlador.obtenerLibros();

        //asignar los libros a las librerias
        if (libros.size() >= 8) {
            libreria1.setLibros(libros.subList(0, 4));
            libreria2.setLibros(libros.subList(4, 8));

            //guardar las librerias

            controlador.crearLibreria(libreria1, libreria1.getLibros());
            controlador.crearLibreria(libreria2, libreria2.getLibros());


        }
        else {
            System.out.println("No hay suficientes libros en la base de datos.");
        }

        // Mostrar todos los libros con su autor y editorial
        System.out.println("Libros por autor y editorial:\n");
        mostrarLibrosConAutorYEditorial(controlador);

        // Mostrar todos los autores con sus libros
        System.out.println("Autores y sus libros:\n");
        mostrarAutoresConLibros(controlador);

        // Mostrar todas las librerías con sus libros
        System.out.println("Librerias y libros:\n");
        mostrarLibreriasConLibros(controlador);

        // Mostrar todos los libros con las librerías en las que están
        System.out.println("Libros y librerias en las que se encuentran:\n");
        mostrarLibrosConLibrerias(controlador);
    }

    // mostrar todos los libros con su autor y editorial
    public static void mostrarLibrosConAutorYEditorial(Controlador controlador) {
        List<Libro> libros = controlador.obtenerLibrosConAutorYEditorial();
        for (Libro libro : libros) {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre() + " " + libro.getAutor().getApellidos());
            System.out.println("Editorial: " + libro.getEditorial().getNombre());
            System.out.println("------------------------------");
        }
    }

    // mostrar todos los autores con sus libros
    public static void mostrarAutoresConLibros(Controlador controlador) {
        List<Autor> autores = controlador.obtenerAutoresConLibros();
        for (Autor autor : autores) {
            System.out.println("Autor: " + autor.getNombre() + " " + autor.getApellidos());
            System.out.println("Libros: ");
            for (Libro libro : autor.getLibros()) {
                System.out.println(" - " + libro.getTitulo());
            }
            System.out.println("------------------------------");
        }
    }

    // mostrar todas las librerías con sus libros
    public static void mostrarLibreriasConLibros(Controlador controlador) {
        List<Libreria> librerias = controlador.obtenerLibreriasConLibros();
        for (Libreria libreria : librerias) {
            System.out.println("Librería: " + libreria.getNombre());
            System.out.println("Libros: ");
            for (Libro libro : libreria.getLibros()) {
                System.out.println(" - " + libro.getTitulo());
            }
            System.out.println("------------------------------");
        }
    }

    // mostrar todos los libros con las librerías en las que están
    public static void mostrarLibrosConLibrerias(Controlador controlador) {
        List<Libro> libros = controlador.obtenerLibrosConLibrerias();
        for (Libro libro : libros) {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Librerías: ");
            for (Libreria libreria : libro.getLibrerias()) {
                System.out.println(" - " + libreria.getNombre());
            }
            System.out.println("------------------------------");
        }
    }
}

