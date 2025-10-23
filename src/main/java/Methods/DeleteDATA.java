package Methods;

import Conexion.ConexionMySQL;

import java.util.Scanner;

public class DeleteDATA {
    public static void DeleteDATA(String nombre, String DNI, Scanner sc) {
        // Create Connection
        ConexionMySQL Conexion = new ConexionMySQL("root", " ", "estudiantes");

        System.out.println("Inserta el DNI del estudiante al que le quieres cambiar datos");
        DNI = sc.nextLine();

        // Check if the student exists for updating
        while (!UpdateDATA.EstudianteExiste(Conexion, DNI)) {
            System.err.println("El estudiante con DNI: " + DNI + " no existe en la base de datos.");
            System.out.println("Introduce un DNI existente en la base de datos");
            DNI = sc.nextLine();
        }
        Delete(Conexion, DNI);
    }

    // Method to delete student data
    public static void Delete(ConexionMySQL Conexion, String DNI) {
        try {
            // Connect to the database
            Conexion.conectar();
            // Insert statement
            String delete = "DELETE FROM ESTUDIANTES WHERE dni = '" + DNI + "'";
            // Execute the insert
            Conexion.ejecutarInsertDeleteUpdate(delete);
            // Exit the connection
            Conexion.desconectar();
            System.out.println("ESTUDIANTE ELIMINADO CORRECTAMENTE");
        } catch (Exception e) {
            // Print error message
            System.err.println(e.getMessage());
        }
    }
}

