package Controller;

import DAO.CocheDao;
import DAO.PasajeroDao;
import model.Coche;
import model.Pasajero;
import java.sql.SQLException;
import java.util.ArrayList;

public class Concesionario {
    private CocheDao cocheDao;
    private PasajeroDao pasajeroDao;

    public Concesionario() throws SQLException {
        cocheDao = new CocheDao();
        pasajeroDao = new PasajeroDao();
    }

    // ---------------- Métodos de gestión de coches ----------------

    // Añadir coche
    public void añadirCoche(Coche coche) throws SQLException {
        cocheDao.añadirCoche(coche);
    }

    // Borrar coche por ID
    public void borrarCoche(int id) throws SQLException {
        cocheDao.borrarCoche(id);
    }

    // Consultar coche por ID
    public Coche consultarCochePorId(int id) throws SQLException {
        ArrayList<Coche> coches = cocheDao.getCocheId(id);
        if(coches.isEmpty()){
            return null;
        }
        else{
            return coches.get(0);
        }
    }

    // Modificar coche
    public void modificarCoche(int id, Coche coche) throws SQLException {
        cocheDao.modificarCoche(id, coche);
    }

    // Listar todos los coches
    public ArrayList<Coche> listarCoches() throws SQLException {
        return cocheDao.listaCoches();
    }

    // ---------------- Métodos de gestión de pasajeros ----------------

    // Añadir pasajero
    public void añadirPasajero(Pasajero pasajero) throws SQLException {
        pasajeroDao.añadirPasajero(pasajero);
    }

    // Borrar pasajero por ID
    public void borrarPasajero(int id) throws SQLException {
        pasajeroDao.borrarPasajero(id);
    }

    // Consultar pasajero por ID
    public ArrayList<Pasajero> getPasajeroId(int id) throws SQLException {
        return pasajeroDao.getPasajeroId(id);
    }

    // Listar todos los pasajeros
    public ArrayList<Pasajero> listaPasajeros() throws SQLException {
        return pasajeroDao.listaPasajeros();
    }

    // ---------------- Métodos de asociación pasajeros-coches ----------------

    // Añadir pasajero a coche
    public void añadirPasajeroACoche(int idCoche, int idPasajero) throws SQLException {
        pasajeroDao.añadirPasajeroCoche(idCoche, idPasajero);
    }

    // Eliminar pasajero de coche
    public void eliminarPasajeroCoche(int idPasajero, int idCoche) throws SQLException {
        pasajeroDao.eliminarPasajeroCoche(idPasajero);
    }

    // Listar todos los pasajeros de un coche
    public ArrayList<Pasajero> listarPasajerosCoche(int idCoche) throws SQLException {
        return pasajeroDao.listarPasajerosCoche(idCoche);
    }
}
