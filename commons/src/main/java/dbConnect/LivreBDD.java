package dbConnect;

import modele.Livre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dbConnect.Connexion.createConnexion;

public class LivreBDD {

    public static List<Livre> getLivres() throws SQLException {
        //liste de tout les livres
        String qry = "SELECT * FROM livre";
        List<Livre> l = new ArrayList<Livre>();

        Connection connection = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(qry);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String titre = resultSet.getString("titre");
            double prix = resultSet.getDouble("prix");
            int quantite = resultSet.getInt("quantite");
            String description = resultSet.getString("description");
            l.add(new Livre(id, titre, prix, quantite, description));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return l;
    }

    public static void ajouterLivre(Livre livre) throws SQLException {
        //liste de tout les livres
        List<Livre> l = getLivres();

        if (!l.contains(livre)) {
            Connection connection = createConnexion();
            PreparedStatement st = connection.prepareStatement("INSERT INTO livre (titre, prix, quantite, description) VALUES (?, ?, ?, ?)");
            st.setString(1, livre.getTitre());
            st.setDouble(2, livre.getPrix());
            st.setInt(3, livre.getQuantite());
            st.setString(4, livre.getDescription());

            st.executeUpdate();
            st.close();
            connection.close();
        } else {
            System.out.println("Livre déjà existant");
        }

    }


    // Retourne un livre selon son ID, retourne null si aucun livre ne correspond
    public static Livre getLivreById(int id) throws SQLException {
        String qry = "SELECT * FROM livre WHERE id=" + id;
        Livre l = null;

        Connection connection = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(qry);
        if (resultSet.next()) {
            do {
                String titre = resultSet.getString("titre");
                double prix = resultSet.getDouble("prix");
                int quantite = resultSet.getInt("quantite");
                String description = resultSet.getString("description");
                l = new Livre(id, titre, prix, quantite, description);
            } while (resultSet.next());
        }
        connection.close();
        statement.close();
        resultSet.close();
        return l;
    }

    public static Livre getLivreByTitre(String titre) throws SQLException {
        String qry = "SELECT * FROM livre WHERE titre=" + titre;
        Livre l = null;

        Connection connection = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(qry);

        if (resultSet.next()) {
            do {
                int id = resultSet.getInt("id");
                double prix = resultSet.getDouble("prix");
                int quantite = resultSet.getInt("quantite");
                String description = resultSet.getString("description");
                l = new Livre(id, titre, prix, quantite, description);
            } while (resultSet.next());
        }
        connection.close();
        statement.close();
        resultSet.close();
        return l;
    }

    public static void baisserQteLivreByID(int id)throws SQLException {
        Livre livre = getLivreById(id);
        int qteActuelle = livre.getQuantite();
        int nouvelleQte = qteActuelle - 1;
        String qry = "UPDATE livre SET quantite = '" + nouvelleQte + "' WHERE id = " + id;

        Connection connection = createConnexion();
        Statement statement = null;
        statement = connection.createStatement();
        statement.executeUpdate(qry);

        connection.close();
        statement.close();
    }
}
