package dao;

import database.HibernateUtil;
import model.Autor;
import model.Editorial;
import model.Libreria;
import model.Libro;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LibreriaDao {
    private Session session;

    public void crearLibreria(Libreria libreria){

        //ABRO
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        //EMPIEZO
        session.beginTransaction();
        //GUARDO
        session.persist(libreria);
        //GARANTIZO
        session.getTransaction().commit();
        //CIERRO
        session.close();
    }

    public List<Libreria> obtenerLibreriasConLibros() {
        Session session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String hql = "SELECT l FROM Libreria l JOIN FETCH l.libros";
        Query<Libreria> query = session.createQuery(hql, Libreria.class);
        List<Libreria> librerias = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return librerias;
    }


}
