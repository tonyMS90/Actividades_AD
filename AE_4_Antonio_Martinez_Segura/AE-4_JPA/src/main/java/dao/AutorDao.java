package dao;


import database.HibernateUtil;
import model.Autor;
import model.Libro;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class AutorDao {
    private Session session;

    public void crearAutor(Autor autor){
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(autor);
        session.getTransaction().commit();
        session.close();
    }

    public List<Autor> obtenerAutoresConLibros() {
        Session session = new HibernateUtil().getSessionFactory().getCurrentSession();
        session.beginTransaction();

        String hql = "SELECT a FROM Autor a JOIN FETCH a.libros";
        Query<Autor> query = session.createQuery(hql, Autor.class);
        List<Autor> autores = query.getResultList();

        session.getTransaction().commit();
        session.close();

        return autores;
    }



}
