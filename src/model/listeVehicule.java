package model;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class listeVehicule {
    private ArrayList<vehicule> maListe = new ArrayList<>();

    public listeVehicule(){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM vehicule;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String marque = rs.getString("marque");
                String modele = rs.getString("modele");
                int annee = rs.getInt("annee");
                String type = rs.getString("type");
                int puissance = rs.getInt("puissance");
                String transmission = rs.getString("transmission");
                int categorie = rs.getInt("categorie");
                int disponilite = rs.getInt("disponibilite");

                vehicule v = new vehicule(id, marque, modele, annee, type, puissance, transmission, categorie, disponilite);

                maListe.add(v);
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void display(){
        for (vehicule v : maListe) {
            v.afficherAttributs();
        }
    }
}
