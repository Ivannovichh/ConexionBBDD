package Main;

import java.util.Scanner;
import Methods.*;

public class Main {
    public static void main(String[] args) {
        // Variables
        String nombre = null;
        int edad = 0;
        String DNI = null;
        int option = 0;
        Scanner sc = new Scanner(System.in);

        // Menu
        do {
            // Show the menu options
            System.out.println("Seleccione una opción:");
            System.out.println("1. Consultar datos");
            System.out.println("2. Insertar datos");
            System.out.println("3. Actualizar datos");
            System.out.println("4. Eliminar datos");
            System.out.println("0. Salir");

            // Read the option
            option = sc.nextInt();
            sc.nextLine(); // consume the newline character after the integer input

            // Handle options
            switch (option) {
                case 0: // Exit
                    break;
                case 1: // Consult
                    consultarDATA.ConsultarData(nombre, edad, DNI, sc);
                    break;
                case 2: // Insert
                    InsertarDATA.InsertarData(nombre, edad, DNI, sc);
                    break;
                case 3: // Update
                    UpdateDATA.UpdateData(nombre, edad, DNI, sc);
                    break;
                case 4: // Delete
                    DeleteDATA.DeleteDATA(nombre, DNI, sc);
                    break;
                default:
                    System.out.println("Opción no válida, por favor elija una opción válida.");
                    break;
            }
        } while (option != 0);

        // Close scanner
        sc.close();
    }
}
