package Methods;

import Conexion.ConexionMySQL;
import java.util.Scanner;


public class InsertarDATA {
    public static void InsertarData(String nombre, int edad, String DNI, Scanner sc) {

        // Create Connection
        ConexionMySQL Conexion = new ConexionMySQL("root", " ", "estudiantes");

        // Insert variables
        System.out.println("Ingrese el nombre del estudiante: ");
        nombre = sc.nextLine();
        System.out.println("Ingrese la edad del estudiante: ");
        edad = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el DNI del estudiante: ");
        DNI = sc.nextLine();

        Insert(Conexion, nombre, edad, DNI);
    }

    // Method to insert student data
    public static void Insert(ConexionMySQL Conexion, String nombre, int edad, String dni) {
        try {
            // Connect to the database
            Conexion.conectar();
            // Insert statement
            String insert = "INSERT INTO ESTUDIANTES (nombre, edad, dni) VALUES ( '" + nombre + "', '" + edad + "', '" + dni + "')";
            // Execute the insert
            Conexion.ejecutarInsertDeleteUpdate(insert);
            // Exit the connection
            Conexion.desconectar();
            System.out.println("ESTUDIANTE INSERTADO CORRECTAMENTE");
        } catch (Exception e) {
            // Print error message
            System.err.println(e.getMessage());
        }
    }
}
