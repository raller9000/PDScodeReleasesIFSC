package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class FabricaConexao {
    private static final String URL = "jdbc:mysql://localhost:3306/supermercado";
    private static final String USER = "root";
    private static final String SENHA = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, SENHA);
        } catch (Exception e) {
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }
}
