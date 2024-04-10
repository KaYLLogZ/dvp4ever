package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class connexion {
    private Connection connection = null;

    public connexion(){
        try {
            // Chargement du pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connexion à la base de données
            String url = "jdbc:mysql://localhost:3306/for_dvp";
            String utilisateur = "root";
            String motDePasse = "ri52phpc";
            connection = DriverManager.getConnection(url, utilisateur, motDePasse);

            // Si la connexion est établie avec succès, vous pouvez effectuer des opérations sur la base de données ici
            System.out.println("Connexion réussie");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Fermez la connexion lorsque vous avez terminé
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
