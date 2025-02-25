package controller;

import dao.AutorDao;
import dao.EditorialDao;
import dao.LibreriaDao;
import dao.LibroDao;
import database.HibernateUtil;
import model.Autor;
import model.Editorial;
import model.Libreria;
import model.Libro;
import org.hibernate.Session;

import java.util.List;

public class Controlador {

    private AutorDao autorDao;
    private EditorialDao editorialDao;
    private LibroDao libroDao;
    private LibreriaDao libreriaDao;

    public Controlador(){
        autorDao = new AutorDao();
        editorialDao = new EditorialDao();
        libroDao = new LibroDao();
        libreriaDao = new LibreriaDao();
    }

    public void crearAutor(Autor autor) {

        autorDao.crearAutor(autor);

    }

    public void crearEditorial(Editorial editorial){

        editorialDao.crearEditorial(editorial);
    }

    public void crearLibro(Libro libro, int idAutor, int idEditorial){
        Session session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Autor autor = session.get(Autor.class, idAutor);
        Editorial editorial = session.get(Editorial.class, idEditorial);
        session.getTransaction().commit();
        session.close();
        libroDao.crearLibro(libro, autor, editorial);
    }

    // Método para crear una librería
    public void crearLibreria(Libreria libreria, List<Libro> libros) {
        Session session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Asociamos los libros con la librería
        libreria.setLibros(libros);

        // Guardamos la librería con los libros asociados
        session.persist(libreria);

        session.getTransaction().commit(); // Confirmamos la transacción
        session.close(); // Cerramos la sesión
    }
    //metodo para obtener libros

    public List<Libro> obtenerLibros() {
        return libroDao.obtenerLibros();
    }

    // Mostrar libros con editorial y autor
    public List<Libro> obtenerLibrosConAutorYEditorial() {
        return libroDao.obtenerLibrosConAutorYEditorial();
    }

    // Mostrar autores con sus libros asociados
    public List<Autor> obtenerAutoresConLibros() {
        return autorDao.obtenerAutoresConLibros();
    }

    // Mostrar las librerias con libros asociados
    public List<Libreria> obtenerLibreriasConLibros() {
        return libreriaDao.obtenerLibreriasConLibros();
    }

    // Mostrar libros y libreria en la que se encuentran
    public List<Libro> obtenerLibrosConLibrerias() {
        return libroDao.obtenerLibrosConLibrerias();
    }


}
