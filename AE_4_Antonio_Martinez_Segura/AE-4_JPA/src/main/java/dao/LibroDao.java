package dao;

import database.HibernateUtil;
import model.Autor;
import model.Editorial;
import model.Libreria;
import model.Libro;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LibroDao {

    private Session session;

    //crear libro
    public void crearLibro(Libro libro, Autor autor, Editorial editorial){

        //ABRO
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        //EMPIEZO
        session.beginTransaction();
        libro.setEditorial(editorial);
        libro.setAutor(autor);
        session.merge(libro);
        //GARANTIZO
        session.getTransaction().commit();
        //CIERRO
        session.close();
    }

    //metodo para obtener libros
    public List<Libro> obtenerLibros() {
        // Usamos getCurrentSession() para obtener la sesión actual
        Session session = new HibernateUtil().getSessionFactory().getCurrentSession();

        // Iniciar transacción
        session.beginTransaction();

        // Realizamos la consulta HQL para obtener todos los libros
        Query<Libro> query = session.createQuery("FROM Libro", Libro.class);
        List<Libro> libros = query.list();

        // Commit de la transacción
        session.getTransaction().commit();

        // No es necesario cerrar la sesión manualmente, Hibernate lo maneja
        return libros;
    }

    public List<Libro> obtenerLibrosConAutorYEditorial() {
        Session session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String hql = "SELECT l FROM Libro l JOIN FETCH l.autor JOIN FETCH l.editorial";
        Query<Libro> query = session.createQuery(hql, Libro.class);
        List<Libro> libros = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return libros;
    }

    public List<Libro> obtenerLibrosConLibrerias() {
        Session session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String hql = "SELECT libro FROM Libro libro JOIN FETCH libro.librerias";
        Query<Libro> query = session.createQuery(hql, Libro.class);
        List<Libro> libros = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return libros;
    }


}
