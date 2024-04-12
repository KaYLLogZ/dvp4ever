package model;
import java.util.*;
import java.sql.*;

public class listeVehicule {
    private ArrayList<vehicule> maListe = new ArrayList<>();
    private String filtreMarque = null;
    private String filtreModele = null;
    private int filtreAnnee = 0;
    private String filtreType = null;
    private int filtrePuissance = 0;
    private String filtreTransmission = null;
    private int filtreCategorie = 0;

    public listeVehicule(String fm, String fm2, int fa, String ft, int fp, String ft2, int fc){
        this.filtreMarque = fm;
        this.filtreModele = fm2;
        this.filtreAnnee = fa;
        this.filtreType = ft;
        this.filtrePuissance = fp;
        this.filtreTransmission = ft2;
        this.filtreCategorie = fc;
    }

    public void chargeListe(){
        maListe.clear();

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")) {
            /*
            String sql = "SELECT * FROM vehicule WHERE (? AND ? AND ? AND ? AND ? AND ? AND ?);";
            PreparedStatement stmt = conn.prepareStatement(sql);

            if(filtreMarque == null) stmt.setString(1, "1 = 1");
            else stmt.setString(1, "marque = " + filtreMarque);

            if(filtreModele == null) stmt.setString(2, "1 = 1");
            else stmt.setString(2, "modele = " + filtreModele);

            if(filtreAnnee == 0) stmt.setString(3, "1 = 1");
            else stmt.setString(3, "annee > " + filtreAnnee);

            if(filtreType == null) stmt.setString(4, "1 = 1");
            else stmt.setString(4, "type = " + filtreType);

            if(filtrePuissance == 0) stmt.setString(5, "1 = 1");
            else stmt.setString(5, "puissance > " + filtrePuissance);

            if(filtreTransmission == null) stmt.setString(6, "1 = 1");
            else stmt.setString(6, "transmission = " + filtreTransmission);

            if(filtreCategorie == 0) stmt.setString(7, "1 = 1");
            else stmt.setString(7, "categorie = " + filtreCategorie);
            */

            String sql = "SELECT * FROM vehicule WHERE ";

            ArrayList<String> conditions = new ArrayList<>();
            if (filtreMarque != null) {
                conditions.add("marque = ?");
            }
            if (filtreModele != null) {
                conditions.add("modele = ?");
            }
            if (filtreAnnee != 0) {
                conditions.add("annee > ?");
            }
            if (filtreType != null) {
                conditions.add("type = ?");
            }
            if (filtrePuissance != 0) {
                conditions.add("puissance > ?");
            }
            if (filtreTransmission != null) {
                conditions.add("transmission = ?");
            }
            if (filtreCategorie != 0) {
                conditions.add("categorie = ?");
            }

            if(conditions.size() == 0) conditions.add("'1 = 1'");

            sql += String.join(" AND ", conditions);

            PreparedStatement stmt = conn.prepareStatement(sql);


            int paramIndex = 1;
            if (filtreMarque != null) {
                stmt.setString(paramIndex++, filtreMarque);
            }
            if (filtreModele != null) {
                stmt.setString(paramIndex++, filtreModele);
            }
            if (filtreAnnee != 0) {
                stmt.setInt(paramIndex++, filtreAnnee);
            }
            if (filtreType != null) {
                stmt.setString(paramIndex++, filtreType);
            }
            if (filtrePuissance != 0) {
                stmt.setInt(paramIndex++, filtrePuissance);
            }
            if (filtreTransmission != null) {
                stmt.setString(paramIndex++, filtreTransmission);
            }
            if (filtreCategorie != 0) {
                stmt.setInt(paramIndex++, filtreCategorie);
            }

            ResultSet rs = stmt.executeQuery();

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

    public void afficheList(){
        System.out.println("\nFiltres");

        if (filtreMarque != null) {
            System.out.println("Marque : "+ filtreMarque);
        }
        if (filtreModele != null) {
            System.out.println("Modele : "+ filtreModele);
        }
        if (filtreAnnee != 0) {
            System.out.println("Annee > "+ filtreAnnee);
        }
        if (filtreType != null) {
            System.out.println("Type : "+ filtreType);
        }
        if (filtrePuissance != 0) {
            System.out.println("Puissance > "+ filtrePuissance);
        }
        if (filtreTransmission != null) {
            System.out.println("Transmission : "+ filtreTransmission);
        }
        if (filtreCategorie != 0) {
            System.out.println("Categorie : "+ filtreCategorie);
        }

        if(maListe.size() == 0) System.out.println("\nAucun vehicule ne reponds a vos criteres\n");
        else System.out.println("\nListe de nos véhicules :");

        for (vehicule v : maListe) {
            v.preview();
        }
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

    public void resetFiltre(){
        filtreTransmission = null;
        filtrePuissance = 0;
        filtreCategorie = 0;
        filtreMarque = null;
        filtreAnnee = 0;
        filtreModele = null;
        filtreType = null;
    }

    public void setFiltreMarque(String x){
        filtreMarque = x;
    }

    public void setFiltreModele(String x){
        filtreModele = x;
    }

    public void setFiltreType(String x){
        filtreType = x;
    }

    public void setFiltreTransmission(String x){
        filtreTransmission = x;
    }

    public void setFiltreAnnee(int x){
        filtreAnnee = x;
    }

    public void setFiltrePuissance(int x){
        filtrePuissance = x;
    }

    public void setFiltreCategorie(int x){
        filtreCategorie = x;
    }

    public void display(){
        for (vehicule v : maListe) {
            v.afficherAttributs();
        }
    }
}
