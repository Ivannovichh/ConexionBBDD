package Methods;

import java.sql.ResultSet;
import java.util.Scanner;

import Conexion.ConexionMySQL;
import Main.Main;



public class UpdateDATA {
    public static void UpdateData(String nombre, int edad, String DNI, Scanner sc) {
        // Create Connection
        ConexionMySQL Conexion = new ConexionMySQL("root", " ", "estudiantes");
        String insert = " ";

        System.out.println("Inserta el DNI del estudiante al que le quieres cambiar datos");
        DNI = sc.nextLine();

        // Check if the student exists for updating
        while (!EstudianteExiste(Conexion, DNI)) {
            System.err.println("El estudiante con DNI: " + DNI + " no existe en la base de datos.");
            System.out.println("Introduce un DNI existente en la base de datos");
            DNI = sc.nextLine();
        }

        // Insert variables
        System.out.println("Ingrese la sección que quiere modificar (nombre o dni)");
        String Variable = sc.nextLine();
        sc.next();
        System.out.println("Ingresa el nuevo valor");
        String change = sc.nextLine();

        Update(Conexion, Variable, change, insert, DNI);

    }

    //Method to update

    public static void Update(ConexionMySQL Conexion, String Variable, String change, String insert, String DNI) {
        try {
            // Connect
            Conexion.conectar();
            if (Variable.equals("nombre")) {
                // Insert update in name
                insert = "UPDATE ESTUDIANTES" + "SET nombre ='" + change + "' WHERE dni = " + DNI;
            } else if (Variable.equals("dni")) {
                // Insert update in DNI
                insert = "UPDATE ESTUDIANTES" + "SET dni = '" + change + "' WHERE dni = " + DNI;
            } else if (Variable.equals("edad")) {
                // Insert update in age
                insert = "UPDATE ESTUDIANTES" + "SET edad = '" + change + "' WHERE dni = " + DNI;
            }
            // Execute
            Conexion.ejecutarInsertDeleteUpdate(insert);
            // Exit
            Conexion.desconectar();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Method to check if the student exists
    public static boolean EstudianteExiste(ConexionMySQL Conexion, String DNI) {
        try {
            // Connect to the database
            Conexion.conectar();

            // SQL query to find the student by their DNI
            String Select = "SELECT * FROM ESTUDIANTES WHERE DNI = '" + DNI + "'";
            ResultSet rs = Conexion.ejecutarSelect(Select);

            // Check if the student exists
            if (rs.next()) {
                // If a student with this DNI is found, return true
                Conexion.desconectar();
                return true;
            } else {
                // If no student with this DNI is found, return false
                Conexion.desconectar();
                return false;
            }
        } catch (Exception e) {
            // In case of query error, print the error and return false
            System.err.println("Error checking if the student exists: " + e.getMessage());
            return false;
        }
    }
}
