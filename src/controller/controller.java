package controller;
import view.view;
import model.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

public class controller {
    private connexion c = null;
    private listeVehicule lv = null;
    private listeLocation ll = null;
    private listeUtilisateur lu = null;
    private listeClient lc = null;

    public controller(){
        this.lv = new listeVehicule();
        this.ll = new listeLocation();
        this.lu = new listeUtilisateur();
        this.lc = new listeClient();
    }

    public void run(){
        view.accueil();
    }

    public void ajouterClient(client c){
        String sql_rqt = "INSERT INTO client (nom, prenom, mail, pswd, categorie_abonnement, date_inscription, particulier) VALUES (";
        sql_rqt += ("'" + c.getNom() + "', '");
        sql_rqt += (c.getPrenom() + "', '");
        sql_rqt += (c.getMail() + "', '");
        sql_rqt += (c.getPassword() + "', ");
        sql_rqt += (c.getCategorie_abonnement() + ", '");
        sql_rqt += (c.getDate_inscription() + "', ");
        sql_rqt += (c.isParticulier() + ");");

        System.out.println(sql_rqt);

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql_rqt);
            System.out.println("Nombre de lignes affectées : " + rowsAffected);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        int id = 0;

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            Statement stmt = conn.createStatement();

            String sql = "SELECT id FROM client WHERE nom = " + "'" + c.getNom() + "';";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                id = rs.getInt("id");
                System.out.println("id : " + id);
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        c.setId(id);
        lc.addClient(c);
        lc.display();
    }

    public boolean connexionClient(String mail, String password){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            String sql = "SELECT COUNT(*) AS count_matching_combinations FROM client WHERE mail = ? AND pswd = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, mail);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count_matching_combinations");
                return (count > 0);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean connexionEmploye(String mail, String password){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            String sql = "SELECT COUNT(*) AS count_matching_combinations FROM employe WHERE mail = ? AND pswd = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, mail);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count_matching_combinations");
                return (count > 0);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
