package Utilidades;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionSQLite {
 
    private static final String FILE_NAME = "base.db";
    private static final String URL = "jdbc:sqlite:" + Paths.get(FILE_NAME).toAbsolutePath();


    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
            return null;
        }
    }
}


