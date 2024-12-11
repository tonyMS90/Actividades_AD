package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import database.MongoDBConnection;
import model.Alumno;
import model.Profesor;
import org.bson.conversions.Bson;

public class ProfesorDao {

    MongoCollection coleccionProfesores;

    public ProfesorDao(){coleccionProfesores = new MongoDBConnection().getProfesorCollection();}

    public void insertarProfesorPOJO(Profesor profesor){

        MongoCollection collection = new MongoDBConnection().getProfesorCollection();
        collection.insertOne(profesor);
    }

    public void mostrarProfesoresPOJO(){
        MongoCollection collection = new MongoDBConnection().getProfesorCollection();
        FindIterable<Profesor> iterable = collection.find(Profesor.class);
        MongoCursor<Profesor> cursor = iterable.cursor();
        while (cursor.hasNext()){
            Profesor profesor = cursor.next();
            profesor.mostrarDatos();
        }
    }

    public void buscarProfesorPOJO(Integer age){
        MongoCollection <Profesor> collection = new MongoDBConnection().getProfesorCollection();
        FindIterable<Profesor> iterable = collection.find(Filters.eq("age", age));
        MongoCursor<Profesor> cursor = iterable.cursor();
        while (cursor.hasNext()){
            Profesor profesor = cursor.next();
            profesor.mostrarDatos();
        }
    }
    public void actualizarProfesor(String email, double rating){

        MongoCollection<Profesor> collection = new MongoDBConnection().getProfesorCollection();
        Bson filtrado = Filters.eq("email", email);//filtro para encontrar el profesor
        Bson actualizado = Updates.set("rating", rating);//crear actualizacion
        UpdateResult resultado = collection.updateOne(filtrado, actualizado);//realizarla

        if(resultado.getModifiedCount() > 0){
            System.out.println("El profesor ha sido actualizado");
            mostrarProfesoresPOJO();
        }
        else{
            System.out.println("No se ha encontrado ningun profesor con el email introducido");
        }

    }
}
