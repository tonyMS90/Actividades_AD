import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dao.AlumnoDao;
import dao.ProfesorDao;
import database.DBScheme;
import database.MongoDBConnection;
import model.Alumno;
import model.Profesor;

import java.util.Collections;
import java.util.Scanner;

public class Entrada {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        AlumnoDao alumnoDao = new AlumnoDao();
        ProfesorDao profesorDao = new ProfesorDao();

        //conexion
        String connectionString = "mongodb+srv://martinezseguratony:mongo@cluster0.4tpht.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(String.format(connectionString, DBScheme.USER, DBScheme.PASS)))
                .serverApi(serverApi)
                .build();

        MongoClient mongoClient = MongoClients.create(String.format(connectionString, DBScheme.USER, DBScheme.PASS));

        //database

        MongoDatabase database = mongoClient.getDatabase("centro_estudios");

        //colecciones

        MongoCollection collection = database.getCollection("alumnos");
        MongoCollection collection1 = database.getCollection("profesores");


        //inicializo scanner

        while(true){
            menu();
            try {
                int opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1: {
                        insertarProfesor(profesorDao);
                        break;
                    }
                    case 2: {
                        insertarAlumno(alumnoDao);
                        break;
                    }
                    case 3: {
                        mostrarTodosLosDatos(alumnoDao, profesorDao);
                        break;
                    }
                    case 4: {
                        mostrarProfesores(profesorDao);
                        break;
                    }
                    case 5: {
                        mostrarAlumnos(alumnoDao);
                        break;
                    }
                    case 6: {
                        buscarAlumno(alumnoDao);
                        break;
                    }
                    case 7: {
                        buscarProfesor(profesorDao);
                        break;
                    }
                    case 8: {
                        actualizarProfesor(profesorDao);
                        break;
                    }
                    case 9: {
                        eliminarAlumnos(alumnoDao);
                        break;
                    }
                    case 10: {
                        System.out.println("Cerrando el programa...");
                        return;
                    }
                    default: {
                        System.out.println("Introduce una opción válida.");
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido.");
            }
        }
    }

    private static void menu() {
        // Menú de opciones
        System.out.println("\n------- Menú -------");
        System.out.println("1. Insertar profesor");
        System.out.println("2. Insertar alumno");
        System.out.println("3. Mostrar todos los datos");
        System.out.println("4. Mostrar profesores");
        System.out.println("5. Mostrar alumnos");
        System.out.println("6. Buscar alumno");
        System.out.println("7. Buscar profesor");
        System.out.println("8. Actualizar profesor");
        System.out.println("9. Eliminar alumno");
        System.out.println("10. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void insertarProfesor(ProfesorDao profesorDao) {
        System.out.println("Introduce los datos del profesor:");

        // Pedimos los datos del profesor
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Edad: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Género: ");
        String gender = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();
        System.out.print("Subjects (separados por coma): ");
        String subjects = scanner.nextLine();
        System.out.print("Título: ");
        String title = scanner.nextLine();
        System.out.print("Calificación: ");
        double rating = Double.parseDouble(scanner.nextLine());

        // Creamos el objeto Profesor
        Profesor profesor = new Profesor(rating, age, name, gender, email, phone, Collections.singletonList(subjects), title);

        // Insertamos el profesor en la base de datos
        profesorDao.insertarProfesorPOJO(profesor);
        System.out.println("Profesor insertado correctamente.");
    }

    private static void insertarAlumno(AlumnoDao alumnoDao) {
        System.out.println("Introduce los datos del alumno:");

        // Pedimos los datos del alumno
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Edad: ");
        int age = Integer.parseInt(scanner.nextLine());
        System.out.print("Género: ");
        String gender = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();
        System.out.print("Calificación: ");
        int calification = Integer.parseInt(scanner.nextLine());
        System.out.print("Grado superior: ");
        String higherGrade = scanner.nextLine();
        System.out.print("Rating: ");
        double rating = Double.parseDouble(scanner.nextLine());

        // Creamos el objeto Alumno
        Alumno alumno = new Alumno(rating, age, name, gender, email, phone, calification, higherGrade);

        // Insertamos el alumno en la base de datos
        alumnoDao.insertarAlumnoPOJO(alumno);
        System.out.println("Alumno insertado correctamente.");
    }

    private static void mostrarTodosLosDatos(AlumnoDao alumnoDao, ProfesorDao profesorDao) {
        System.out.println("Mostrando todos los datos:");

        // Mostramos los alumnos y los profesores
        alumnoDao.mostrarAlumnosPOJO();
        profesorDao.mostrarProfesoresPOJO();
    }

    private static void mostrarProfesores(ProfesorDao profesorDao) {
        System.out.println("Mostrando todos los profesores:");
        profesorDao.mostrarProfesoresPOJO();
    }

    private static void mostrarAlumnos(AlumnoDao alumnoDao) {
        System.out.println("Mostrando todos los alumnos:");
        alumnoDao.mostrarAlumnosPOJO();
    }

    private static void buscarAlumno(AlumnoDao alumnoDao) {
        System.out.print("Introduce el email del alumno a buscar: ");
        String email = scanner.nextLine();
        alumnoDao.buscarAlumnoPOJO(email);
    }

    private static void buscarProfesor(ProfesorDao profesorDao) {
        System.out.print("Introduce la edad del profesor a buscar: ");
        int age = Integer.parseInt(scanner.nextLine());
        profesorDao.buscarProfesorPOJO(age);
    }

    private static void actualizarProfesor(ProfesorDao profesorDao) {
        System.out.print("Introduce el email del profesor a actualizar: ");
        String email = scanner.nextLine();
        System.out.print("Introduce la nueva calificación: ");
        double rating = Double.parseDouble(scanner.nextLine());
        profesorDao.actualizarProfesor(email, rating);
    }

    private static void eliminarAlumnos(AlumnoDao alumnoDao) {
        System.out.println("Eliminando alumnos con calificación >= 5...");
        alumnoDao.eliminarAlumnoPOJO(5);
    }
}
