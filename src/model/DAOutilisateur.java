package model;
import java.sql.*;

public class DAOutilisateur {
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

    public void changeAbonnement(int IdClient, int newAbo){
        String sql_rqt2 = "UPDATE client SET categorie_abonnement = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/turismo_db", "root", "ri52phpc");
             PreparedStatement stmt = conn.prepareStatement(sql_rqt2)) {
            stmt.setInt(1, newAbo);
            stmt.setInt(2, IdClient);
            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
