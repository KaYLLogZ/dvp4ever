package model;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class listeLocation {
    private ArrayList<location> maListe = new ArrayList<>();

    public listeLocation(){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM location;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                int idClient = rs.getInt("id_client");
                int idVehicule = rs.getInt("id_vehicule");
                Date debut = rs.getDate("date_debut");
                Date fin= rs.getDate("date_fin");

                location l = new location(id, idClient, idVehicule, debut, fin);

                maListe.add(l);
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void display(){
        for (location l : maListe) {
            l.afficherAttributs();
        }
    }
}
