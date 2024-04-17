package model;

import java.sql.*;
import java.util.ArrayList;

public class DAOlocation {
    public void ajouterLocation(int idClient,int idVehicule, ArrayList<Integer> dL){
        String dateDebut = String.format("%04d-%02d-%02d", dL.get(0), dL.get(1), 01);
        String dateFin = String.format("%04d-%02d-%02d", dL.get(2), dL.get(3), 28);

        String sql_rqt = "INSERT INTO location (id_client, id_vehicule, date_debut, date_fin) VALUES (";
        sql_rqt += (idClient + ", ");
        sql_rqt += (idVehicule + ", '");
        sql_rqt += (dateDebut + "', '");
        sql_rqt += (dateFin + "');");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql_rqt);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int dispoVehiculePeriode(int idVehicule, int Annee, int mois){
        int dispo = 0;
        int use = 0;

        String sql_rqt1 = "SELECT disponibilite FROM vehicule WHERE id = " + idVehicule + ";";


        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")){
            PreparedStatement stmt = conn.prepareStatement(sql_rqt1);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dispo = rs.getInt("disponibilite");
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        String sql_rqt2 = "SELECT COUNT(*) FROM location WHERE id_vehicule = " + idVehicule +
                " AND YEAR(date_debut) <= " + Annee +
                " AND YEAR(date_fin) >= " + Annee +
                " AND MONTH(date_debut) <= " + mois +
                " AND MONTH(date_fin) >= " + mois + ";";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")){
            PreparedStatement stmt = conn.prepareStatement(sql_rqt2);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                use = rs.getInt("COUNT(*)");
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return (dispo - use);
    }

    public boolean dispoVehicule(int idVehicule, int AnneeDebut, int moisDebut, int AnneeFin, int moisFin){
        boolean the_back = true;
        if(AnneeDebut != AnneeFin){
            for(int i=AnneeDebut; i<= AnneeFin; i++){
                boolean secu = true;
                if(i == AnneeDebut){
                    for(int j=moisDebut; j<= 12; j++){
                        if(dispoVehiculePeriode(idVehicule, i, j) <= 0) the_back = false;
                    }
                    secu = false;
                }
                if(i == AnneeFin){
                    for(int j=1; j<= moisFin; j++){
                        if(dispoVehiculePeriode(idVehicule, i, j) <= 0) the_back = false;
                    }
                    secu = false;
                }
                if(secu){
                    for(int j=1; j<= 12; j++){
                        if(dispoVehiculePeriode(idVehicule, i, j) <= 0) the_back = false;
                    }
                }
            }
        }
        else {
            for(int i=moisDebut; i<= moisFin; i++){
                if(dispoVehiculePeriode(idVehicule, AnneeDebut, i) <= 0) the_back = false;
            }
        }

        return the_back;
    }

    public boolean dispoClientPeriode(int idClient, int Annee, int mois){
        int secu = 0;

        String sql_rqt2 = "SELECT COUNT(*) FROM location WHERE id_client = " + idClient +
                " AND YEAR(date_debut) <= " + Annee +
                " AND YEAR(date_fin) >= " + Annee +
                " AND MONTH(date_debut) <= " + mois +
                " AND MONTH(date_fin) >= " + mois + ";";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")){
            PreparedStatement stmt = conn.prepareStatement(sql_rqt2);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                secu = rs.getInt("COUNT(*)");
            }

            rs.close();
            stmt.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return(secu <= 0);
    }

    public boolean dispoClient(int idClient, int AnneeDebut, int moisDebut, int AnneeFin, int moisFin){
        boolean the_back = true;
        if(AnneeDebut != AnneeFin){
            for(int i=AnneeDebut; i<= AnneeFin; i++){
                boolean secu = true;
                if(i == AnneeDebut){
                    for(int j=moisDebut; j<= 12; j++){
                        if(!dispoClientPeriode(idClient, i, j)) the_back = false;
                    }
                    secu = false;
                }
                if(i == AnneeFin){
                    for(int j=1; j<= moisFin; j++){
                        if(!dispoClientPeriode(idClient, i, j)) the_back = false;
                    }
                    secu = false;
                }
                if(secu){
                    for(int j=1; j<= 12; j++){
                        if(!dispoClientPeriode(idClient, i, j)) the_back = false;
                    }
                }
            }
        }
        else {
            for(int i=moisDebut; i<= moisFin; i++){
                if(!dispoClientPeriode(idClient, AnneeDebut, i)) the_back = false;
            }
        }

        return the_back;
    }

    public void supprimerLocation(int idLocation){
        String sql_rqt = "DELETE FROM location WHERE id = " + idLocation + " ;";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(sql_rqt);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<Integer>> locQuatreTrimestre(int annee, int mois){
        ArrayList<ArrayList<Integer>> the_back = new ArrayList<>();

        int ad = annee, md = mois, af = ad, mf = md -1;

        for(int i=0; i<4; i++){
            if((mf + 3) > 12){
                af += 1;
                mf = (mf + 3) % 12;
            }
            else
            {
                mf += 3;
            }

            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(ad);
            temp.add(md);
            temp.add(af);
            temp.add(mf);

            String dateDebutString = String.format("'%04d-%02d-%02d'", ad, md, 1);
            String dateFinString = String.format("'%04d-%02d-%02d'", af, mf, 28);

            String sql_rqt = "SELECT COUNT(*) FROM location WHERE date_debut <= " + dateFinString +
                    " AND date_fin >= " + dateDebutString + ";";

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")){
                PreparedStatement stmt = conn.prepareStatement(sql_rqt);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    temp.add(rs.getInt("COUNT(*)"));
                }

                rs.close();
                stmt.close();

                the_back.add(temp);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }

            if((md + 3) > 12){
                ad += 1;
                md = (md + 3) % 12;
            }
            else {
                md += 3;
            }
        }

        return the_back;
    }

    public ArrayList<ArrayList<Integer>> locAnnuelVehicule(int annee, int mois){
        ArrayList<ArrayList<Integer>> the_back = new ArrayList<>();

        String dateDebutString = String.format("'%04d-%02d-%02d'", annee, mois, 1);
        String dateFinString = String.format("'%04d-%02d-%02d'", (annee+1), mois, 28);

        String sql_rqt = "SELECT id_vehicule, COUNT(*) AS nombre_de_locations FROM location WHERE date_debut <= " + dateFinString
        + "AND date_fin >= " + dateDebutString + " GROUP BY id_vehicule;";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc")){
            PreparedStatement stmt = conn.prepareStatement(sql_rqt);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(rs.getInt("id_vehicule"));
                temp.add(rs.getInt("nombre_de_locations"));
                the_back.add(temp);
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
