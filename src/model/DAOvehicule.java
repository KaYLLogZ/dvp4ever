package model;
import java.sql.*;

public class DAOvehicule {
    public int getCateVehicule(int id){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {

            String sql = "SELECT categorie FROM vehicule WHERE id = " + id;
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {;
                return rs.getInt("categorie");
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public void afficheVehicule(int id){
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {

            String sql = "SELECT * FROM vehicule WHERE id = " + id;
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String marque = rs.getString("marque");
                String modele = rs.getString("modele");
                int annee = rs.getInt("annee");
                String type = rs.getString("type");
                int puissance = rs.getInt("puissance");
                String transmission = rs.getString("transmission");
                int categorie = rs.getInt("categorie");
                int disponilite = rs.getInt("disponibilite");

                vehicule v = new vehicule(id, marque, modele, annee, type, puissance, transmission, categorie, disponilite);
                v.afficherAttributs();
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getPrixVoiture(int id){
        String sql_rqt = "SELECT categorie FROM vehicule WHERE id = ";
        sql_rqt += (id + ";");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")){
            PreparedStatement stmt = conn.prepareStatement(sql_rqt);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int cate = rs.getInt("categorie");
                switch(cate){
                    case 1:
                        return 2180;
                    case 2:
                        return 3480;
                    case 3:
                        return 4980;
                    case 4:
                        return 6980;
                    case 5 :
                        return 9980;
                }
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();


        }

        return 0;
    }

    public static void ajouterVehicule(String marque, String modele, int annee, String type, int puissance, String transmission, int categorie, int disponibilite){
        String sql_rqt = "INSERT INTO vehicule (marque, modele, annee, type, puissance, transmission, categorie, disponibilite) VALUES (";
        sql_rqt += ("'" + marque + "', '");
        sql_rqt += (modele + "', ");
        sql_rqt += (annee + ", '");
        sql_rqt += (type + "', ");
        sql_rqt += (puissance + ", '");
        sql_rqt += (transmission + "', ");
        sql_rqt += (categorie + ", ");
        sql_rqt += (disponibilite + ");");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql_rqt);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void supprimerVehicule(int id){
        String sql_rqt = "DELETE FROM vehicule WHERE id = " + id + ";";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql_rqt);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modifierStock(int choix_vehicule, int choix_nb_piece){
        String sql_rqt = "UPDATE vehicule SET disponibilite = disponibilite + " + choix_nb_piece +
        " WHERE id = " + choix_vehicule + ";";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql_rqt);
            // System.out.println("Nombre de lignes affectées : " + rowsAffected);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getNomVehciule(int id){
        String the_back = "";

        String sql_rqt = "SELECT marque, modele FROM vehicule WHERE id = ";
        sql_rqt += (id + ";");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")){
            PreparedStatement stmt = conn.prepareStatement(sql_rqt);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                the_back += rs.getString("marque");
                the_back += " ";
                the_back += rs.getString("modele");
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return the_back;
    }
}
