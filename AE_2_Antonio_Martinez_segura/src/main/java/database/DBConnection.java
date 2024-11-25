package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    //pedir la conexion
    public Connection getConnection() throws SQLException {

        //si no esta
        if(connection==null){
            //la creo
            createConnection();
        }
        //si esta se la doy
        return connection;
    }

    //metodo para crear la conexion
    private void createConnection() throws SQLException {
        //url de conexion
        String url = String.format("jdbc:mysql://%s:%s/%s", SchemaDB.HOST, SchemaDB.PORT, SchemaDB.DATABASE);
        connection = DriverManager.getConnection(url, "root", "");
    }

    //metodo para cerrar la conexion
    public void closeConnection(){
        try{
            connection.close();
        } catch (SQLException e) {
            System.out.println("error al cerrar la conexion");
        }finally {
            connection = null;
        }
    }
}
