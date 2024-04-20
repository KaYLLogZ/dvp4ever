package model;
import java.sql.*;

public class connexion_bdd {
    public static Connection getConnexion(){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
