package database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    //variable estatica para poder crear la conexion (abre eL camino a la bbdd)
    private static SessionFactory sessionFactory;

    //metodo publico para que me de el session factory
    public SessionFactory getSessionFactory(){
        if (sessionFactory ==null){
            createSessionFactory();
        }

        return sessionFactory;
    }
    //metodo para crear el sessionFactoryy
    private void createSessionFactory() {
        sessionFactory = new Configuration().configure().buildSessionFactory();

    }

    // metodo para cerrar la sessionFactory
    public void closeSessionFactory(){
        sessionFactory.close();
        sessionFactory = null;
    }

}
