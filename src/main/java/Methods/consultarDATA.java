package Methods;

import Conexion.ConexionMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class consultarDATA {

    public static void ConsultarData(String nombre, int edad, String DNI, Scanner sc) {
        // Create connection
        ConexionMySQL Conexion = new ConexionMySQL("root", " ", "estudiantes");
        // Variables
        int option;
        String consult = "";

        try {
            // Connection
            Conexion.conectar();

            // MENU
            do {
                System.out.println("Seleccione un número para consultar en la BBDD: " +
                        "\n 1. Consultar todos los datos" +
                        "\n 2. Consultar por nombre" +
                        "\n 3. Consultar por edad" +
                        "\n 4. Consultar por DNI" +
                        "\n 0. SALIR");
                // Select option
                option = sc.nextInt();

                switch (option) {
                    case 1: // Consult all data
                        consult = "SELECT * FROM ESTUDIANTES";
                        break;
                    case 2: // Consult by name
                        // Solicitar al usuario el nombre
                        System.out.println("Introduce el nombre a buscar:");
                        sc.nextLine(); // Consumir el salto de línea
                        String nombreBuscar = sc.nextLine();
                        consult = "SELECT * FROM ESTUDIANTES WHERE nombre ='" + nombreBuscar + "'";
                        break;
                    case 3: // Consult by age
                        // Solicitar al usuario la edad
                        System.out.println("Introduce la edad a buscar:");
                        int edadBuscar = sc.nextInt();
                        consult = "SELECT * FROM ESTUDIANTES WHERE edad = " + edadBuscar;
                        break;
                    case 4: // Consult by DNI
                        // Solicitar al usuario el DNI
                        System.out.println("Introduce el DNI a buscar:");
                        sc.nextLine();
                        String dniBuscar = sc.nextLine();
                        consult = "SELECT * FROM ESTUDIANTES WHERE DNI ='" + dniBuscar + "'";
                        break;
                    case 0: // Exit
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.err.println("Opción no válida. Por favor, intente de nuevo.");
                        continue; // Vuelve al inicio del bucle
                }

                if (option != 0) {
                    // Execute the query and show the results immediately
                    ResultSet rs = Conexion.ejecutarSelect(consult);
                    mostrar(rs);
                }

            } while (option != 0);

            // Disconnect outside the loop
            Conexion.desconectar();

        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
        }
    }

    public static void mostrar(ResultSet rs) {
        try {
            // Check if the ResultSet contains data
            if (rs != null) {
                // Check if there is at least one result
                if (!rs.isBeforeFirst()) {
                    System.out.println("No se encontraron resultados.");
                    return; // Exit the method
                }

                System.out.println("--- Resultados de la consulta ---");
                // Loop through the results and display them
                while (rs.next()) {
                    // Retrieve data from each column
                    String nombre = rs.getString("nombre");
                    int edad = rs.getInt("edad");
                    String dni = rs.getString("dni");

                    // Print the data to the console
                    System.out.println("Nombre: " + nombre + " | Edad: " + edad + " | DNI: " + dni);
                }
            } else {
                // If no results were found, print this message
                System.err.println("No se obtuvieron resultados de la base de datos.");
            }
        } catch (SQLException e) {
            // Print any errors that occur during the data retrieval
            System.err.println("Error al procesar el ResultSet: " + e.getMessage());
        }
    }
}
