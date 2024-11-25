package DAO;

import database.DBConnection;
import database.SchemaDB;
import model.Coche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//esta clase esta destinada a la gestion de los coches contra la bbdd
public class CocheDao {

    //creo la conexion
    private Connection connection;
    //añadir cosas a la BBDD
    private PreparedStatement preparedStatement;
    //devolver resultados
    private ResultSet resultSet;

    //Inicializo la conexion
    public CocheDao() throws SQLException {
        connection = new DBConnection().getConnection();
    }

    //Añadir coche

    public void añadirCoche(Coche coche) throws SQLException {
        //creo la query
        String query = String.format("INSERT INTO %s (%s,%s,%s,%s) VALUES (?,?,?,?)",
        SchemaDB.TAB_CO, SchemaDB.COL_CO_MAT, SchemaDB.COL_CO_MAR, SchemaDB.COL_CO_MOD, SchemaDB.COL_CO_COL);

        //como quiero añadir coches, inicializo PrepareStatment con la query
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());
        preparedStatement.execute();
    }

    //borrar coche por id
    //falta decir en la clase concesionario que si el id no existe, no se puede borrar el coche
    public void borrarCoche(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?",
                SchemaDB.TAB_CO, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    //consultar coche por id
    public ArrayList<Coche> getCocheId(int id) throws SQLException {
        ArrayList<Coche> resultado = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s = ?",
                SchemaDB.TAB_CO, SchemaDB.COL_ID);
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        return getResultados(resultSet);
    }
    //modificar coche
    //faltar indicar en la clase concesionario que si el id es = a uno que ya existe, no se puede realizar la modificacion
    public void modificarCoche(int id, Coche coche) throws SQLException {
        String query = String.format("UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s = ?",
                SchemaDB.TAB_CO, SchemaDB.COL_CO_MAT, SchemaDB.COL_CO_MAR, SchemaDB.COL_CO_MOD, SchemaDB.COL_CO_COL, SchemaDB.COL_ID);

        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, coche.getMatricula());
        preparedStatement.setString(2, coche.getMarca());
        preparedStatement.setString(3, coche.getModelo());
        preparedStatement.setString(4, coche.getColor());
        preparedStatement.setInt(5, id);
        preparedStatement.executeUpdate();
    }


    //Lista de coches
    public ArrayList<Coche> listaCoches() throws SQLException {
        String query = String.format("SELECT * FROM %s",
                SchemaDB.TAB_CO);
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();{
            return getResultados(resultSet);
        }
    }


    //metodo para devolver una lista con los resultados
    //esta lista tambien la utilizo en el metodo getCocheId
    private ArrayList<Coche> getResultados(ResultSet datosResultantes) throws SQLException {
        ArrayList<Coche>listaResultado = new ArrayList<>();
        while(datosResultantes.next()){
            String matricula = resultSet.getString(SchemaDB.COL_CO_MAT);
            String marca = resultSet.getString(SchemaDB.COL_CO_MAR);
            String modelo = resultSet.getString(SchemaDB.COL_CO_MOD);
            String color = resultSet.getString(SchemaDB.COL_CO_COL);
            listaResultado.add(mapearCoche(matricula, marca, modelo, color));
        }
        return listaResultado;
    }
    //mapeo para utilizar en los resultados
    private Coche mapearCoche(String matricula, String marca, String modelo, String color){
        return new Coche(matricula,marca,modelo,color);
    }


}
