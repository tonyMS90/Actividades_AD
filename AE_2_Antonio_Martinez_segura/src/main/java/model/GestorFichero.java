package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorFichero {
    private static final String FICHERO_USUARIOS = "usuarios.obj";
    private List<Usuario> usuarios;

    public GestorFichero(){
        usuarios = cargarUsuarios();
    }

    private static class Usuario implements Serializable {
        private String nombre;
        private String apellido;
        private String correo;
        private String pass;

        public Usuario(String nombre, String apellido, String correo, String pass) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.correo = correo;
            this.pass = pass;
        }

        public String getCorreo() {
            return correo;
        }

        public String getPass() {
            return pass;
        }

        @Override
        public String toString() {
            return "Nombre: " + nombre + ", Apellido: " + apellido + ", Correo: " + correo;
        }
    }

    // Método para registrar un usuario
    public void registrarUsuario(String nombre, String apellido, String correo, String pass) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo)) {
                System.out.println("Error: Ya existe un usuario con ese correo.");
                return;
            }
        }
        Usuario nuevoUsuario = new Usuario(nombre, apellido, correo, pass);
        usuarios.add(nuevoUsuario);
        guardarUsuarios();
        System.out.println("Usuario registrado exitosamente.");
    }

    // Método para listar usuarios
    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println((i + 1) + ". " + usuarios.get(i));
            }
        }
    }

    // Método para comprobar credenciales
    public void comprobarCredenciales(String correo, String pass) {
        for (Usuario u : usuarios) {
            if (u.getCorreo().equals(correo) && u.getPass().equals(pass)) {
                System.out.println("Login correcto.");
                return;
            }
        }
        System.out.println("Login incorrecto.");
    }

    // Método para exportar usuarios a un archivo
    public void exportarUsuarios() {
        guardarUsuarios();
        System.out.println("Usuarios exportados al archivo: " + FICHERO_USUARIOS);
    }

    // Método para guardar usuarios en el archivo
    private void guardarUsuarios() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_USUARIOS))) {
            oos.writeObject(usuarios);
        } catch (IOException e) {
            System.err.println("Error al guardar los usuarios: " + e.getMessage());
        }
    }

    // Método para cargar usuarios del archivo
    @SuppressWarnings("unchecked")
    private List<Usuario> cargarUsuarios() {
        File archivo = new File(FICHERO_USUARIOS);
        if (!archivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los usuarios: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Método principal con menú
    public static void main(String[] args) {
        GestorFichero gestor = new GestorFichero();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de gestión de usuarios:");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Comprobar credenciales");
            System.out.println("4. Exportar usuarios");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Correo: ");
                    String correo = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = scanner.nextLine();
                    gestor.registrarUsuario(nombre, apellido, correo, pass);
                    break;
                case 2:
                    gestor.listarUsuarios();
                    break;
                case 3:
                    System.out.print("Correo: ");
                    correo = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    pass = scanner.nextLine();
                    gestor.comprobarCredenciales(correo, pass);
                    break;
                case 4:
                    gestor.exportarUsuarios();
                    break;
                case 5:
                    System.out.println("¡Adiós!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}







