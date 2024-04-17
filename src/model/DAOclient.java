package model;
import java.sql.*;

public class DAOclient {
    client cl = null;
    public boolean connexionClient(String mail, String password, client cl){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            String sql = "SELECT * FROM client WHERE mail = ? AND pswd = ? GROUP BY id";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, mail);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String mail_ = rs.getString("mail");
                String pswd = rs.getString("pswd");
                int cateAbo = rs.getInt("categorie_abonnement");
                java.sql.Date dateIns = rs.getDate("date_inscription");
                Boolean ins = rs.getBoolean("particulier");

                cl.setAttribut2(id, nom, prenom, mail_, pswd, cateAbo, dateIns, ins);

                return true;
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql_rqt);
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
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        c.setId(id);
    }

    public client getClient(){ return cl; }

    public static void modifierString(int choix_a, String sel, int id){
        String element = null;
        switch(choix_a){
            case 1:
                element = "Nom";
                break;
            case 2:
                element = "Prenom";
                break;
            case 3:
                element = "Mail";
                break;
            case 4:
                element = "Mot de passe";
                break;
        }

        String sql_rqt = "UPDATE client SET " + element + " = '" + sel +
                "' WHERE id = " + id + ";";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql_rqt);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void modifierBool(int choix_a, int id){
        String sql_rqt = "UPDATE client SET particulier = NOT particulier WHERE id = " + id + ";";


        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql_rqt);
            // System.out.println("Nombre de lignes affectées : " + rowsAffected);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
