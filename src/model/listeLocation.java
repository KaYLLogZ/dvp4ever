package model;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;

public class listeLocation {
    private ArrayList<location> maListe = new ArrayList<>();

    public void chargeLocationClient(int idClient){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            Statement stmt = conn.createStatement();

            LocalDate date = LocalDate.now();
            int annee = date.getYear() + 1;
            int mois = date.getMonthValue();

            String sql = "SELECT * FROM location WHERE id_client = "+ idClient + ";";

            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("id");
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

    public boolean isEmpty(){
        return (maListe.isEmpty());
    }

    public void display(){
        for (location l : maListe) {
            l.afficherAttributs();
        }
    }
}
