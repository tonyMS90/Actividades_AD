package DAO;

import database.DBConnection;
import database.SchemaDB;
import model.Pasajero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//clase destinada a la gestion de pasajeros contra la bbdd
public class PasajeroDao {
   private Connection connection;
   private PreparedStatement preparedStatement;
   private ResultSet resultSet;

   //inicializo conexion

    public PasajeroDao() throws SQLException {
        connection = new DBConnection().getConnection();
    }

    //METODOS

    //Añadir pasajeros

    public void añadirPasajero(Pasajero pasajero) throws SQLException {
        String query = String.format("INSERT INTO %s (%s,%s,%s) VALUES (?,?,?)",
                SchemaDB.TAB_PAS, SchemaDB.COL_PAS_NOM, SchemaDB.COL_PAS_ED, SchemaDB.COL_PAS_PES);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, pasajero.getNombre());
        preparedStatement.setInt(2, pasajero.getEdad());
        preparedStatement.setDouble(3, pasajero.getPeso());
        preparedStatement.execute();
    }

    //borrar pasajero por id
    //falta decir en la clase concesionario que si el id no existe, no se puede borrar el coche
    public void borrarPasajero(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemaDB.TAB_PAS, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    //consultar pasajero por id
    public ArrayList<Pasajero> getPasajeroId(int id) throws SQLException {
        ArrayList<Pasajero> resultado = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_PAS, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }

    //Lista de Pasajeros
    public ArrayList<Pasajero> listaPasajeros() throws SQLException {
        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_PAS);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();{
            return getResultados(resultSet);
        }
    }

    //añadir pasajero a un coche

    public void añadirPasajeroCoche(int idPasajero, int idCoche) throws SQLException {
        String query = String.format ("UPDATE %s SET %s = ? WHERE %s = ?",
                SchemaDB.TAB_PAS, SchemaDB.COL_ID, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche);//Se asigna coche al pasajero
        preparedStatement.setInt(2, idPasajero);//identifica al  pasjero
        preparedStatement.executeUpdate();
    }


    //eliminar pasajero a un coche
    public void eliminarPasajeroCoche(int idPasajero) throws SQLException {
        String query = String.format("UPDATE %s SET %s = NULL WHERE %s = ?",
                SchemaDB.TAB_PAS, SchemaDB.COL_ID, SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idPasajero); // Identifica al pasajero
        preparedStatement.executeUpdate();
    }
    // lista de pasajeros de un coche
    public ArrayList<Pasajero> listarPasajerosCoche(int idCoche) throws SQLException {
        ArrayList<Pasajero> pasajeros = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_PAS, SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idCoche); // Filtra por el id del coche
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            pasajeros.add(new Pasajero(
                    resultSet.getString(SchemaDB.COL_PAS_NOM),
                    resultSet.getInt(SchemaDB.COL_PAS_ED),
                    resultSet.getDouble(SchemaDB.COL_PAS_PES)
            ));
        }
        return pasajeros;
    }




    //metodo para devolver una lista con los resultados
    //esta lista tambien la utilizo en el metodo getPasajeroId
    private ArrayList<Pasajero> getResultados(ResultSet datosResultantes) throws SQLException {
        ArrayList<Pasajero>listaResultado = new ArrayList<>();
        while(datosResultantes.next()){
            String nombre = resultSet.getString(SchemaDB.COL_PAS_NOM);
            int edad = resultSet.getInt(SchemaDB.COL_PAS_ED);
            double peso = resultSet.getDouble(SchemaDB.COL_PAS_PES);
            listaResultado.add(mapearPasajero(nombre, edad, peso));
        }
        return listaResultado;
    }
    //mapeo para utilizar en los resultados
    private Pasajero mapearPasajero(String nombre, int edad, double peso) throws SQLException {
        return new Pasajero(nombre, edad, peso);
    }


}
