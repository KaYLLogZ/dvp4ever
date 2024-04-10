package model;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class listeUtilisateur {
    private ArrayList<utilisateur> maListe = new ArrayList<>();

    public listeUtilisateur(){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM employe;";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String mail = rs.getString("mail");
                String password = rs.getString("pswd");

                utilisateur u = new utilisateur(id, nom, prenom, mail, password);

                maListe.add(u);
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void display(){
        for (utilisateur u : maListe) {
            u.afficherAttributs();
        }
    }
}
