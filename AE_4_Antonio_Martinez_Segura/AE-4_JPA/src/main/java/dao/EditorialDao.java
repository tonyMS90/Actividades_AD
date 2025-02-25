package dao;

import database.HibernateUtil;
import model.Editorial;
import org.hibernate.Session;

public class EditorialDao {
    private Session session;

    public void crearEditorial(Editorial editorial){

        //ABRO
        session = new HibernateUtil().getSessionFactory().getCurrentSession();
        //EMPIEZO
        session.beginTransaction();
        //GUARDO
        session.persist(editorial);
        //GARANTIZO
        session.getTransaction().commit();
        //CIERRO
        session.close();
    }


}
