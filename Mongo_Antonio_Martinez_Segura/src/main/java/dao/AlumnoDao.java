package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import database.MongoDBConnection;
import model.Alumno;
import org.bson.conversions.Bson;

public class AlumnoDao {

    MongoCollection coleccionAlumnos;

    public AlumnoDao(){coleccionAlumnos = new MongoDBConnection().getAlumnoCollection();}

    public void insertarAlumnoPOJO(Alumno alumno){

        MongoCollection collection = new MongoDBConnection().getAlumnoCollection();
        collection.insertOne(alumno);

    }

    public void mostrarAlumnosPOJO(){
        MongoCollection collection = new MongoDBConnection().getAlumnoCollection();
        FindIterable<Alumno> iterable = collection.find(Alumno.class);
        MongoCursor<Alumno> cursor = iterable.cursor();
        while (cursor.hasNext()){
            Alumno alumno = cursor.next();
            alumno.mostrarDatos();
        }
    }

    public void buscarAlumnoPOJO(String email){
        MongoCollection <Alumno> collection = new MongoDBConnection().getAlumnoCollection();
        FindIterable<Alumno> iterable = collection.find(Filters.eq("email", email));
        MongoCursor<Alumno> cursor = iterable.cursor();
        while (cursor.hasNext()){
            Alumno alumno = cursor.next();
            alumno.mostrarDatos();
        }
    }

    public void eliminarAlumnoPOJO(Integer calification){
        Bson filtrado = Filters.gte("calification", 5);
        MongoCollection collection = (MongoCollection) new MongoDBConnection().getAlumnoCollection();
        collection.deleteMany(filtrado);
        System.out.println("alumnos eliminados correctamente");
        mostrarAlumnosPOJO();
    }
}
