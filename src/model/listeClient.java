package model;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class listeClient {
    private ArrayList<client> maListe = new ArrayList<>();

    public listeClient(){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM client;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String mail = rs.getString("mail");
                String password = rs.getString("pswd");
                int cate_abo = rs.getInt("categorie_abonnement");
                Date date_inscri = rs.getDate("date_inscription");
                Boolean particulier = rs.getBoolean("particulier");

                client c = new client(id, nom, prenom, mail, password, cate_abo, date_inscri, particulier);

                maListe.add(c);
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClient(client c){
        maListe.add(c);
    }

    public void display(){
        for (client c : maListe) {
            c.afficherAttributs();
        }
    }
}
