package Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Prueba {
    public static void main(String[] args) {
        ConexionMySQL conexion = new ConexionMySQL("root", " ", "rcp");
        try {
            conexion.conectar();

            String insertar ="INSERT INTO PERSONA VALUES ('Nano', '33')";
            String consulta ="SELECT * FROM PERSONA";
            ResultSet rs = conexion.ejecutarSelect(consulta);
            while (rs.next()) {
                String nombre = rs.getString("Nombre");
                int edad = rs.getInt("Edad");

                System.out.println("Nombre: " + nombre + " | Edad: " + edad);
            }
            String deletear ="DELETE FROM PERSONA";
            conexion.ejecutarInsertDeleteUpdate(insertar);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}