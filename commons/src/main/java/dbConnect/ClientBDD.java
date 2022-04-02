package dbConnect;

import modele.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dbConnect.Connexion.createConnexion;

public class ClientBDD {

    public static List<Client> getClients() throws  SQLException{
        //retourne tout les clients
        String qry = "SELECT * FROM client";
        List<Client> c = new ArrayList<Client>();

        Connection connection = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(qry);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String nom = resultSet.getString("nom");
            String prenom = resultSet.getString("prenom");
            String mail = resultSet.getString("mail");
            String password = resultSet.getString("password");
            c.add(new Client(id, nom, prenom, mail, password));
        }
        connection.close();
        statement.close();
        resultSet.close();
        return c;
    }


    public static Client getClientID(int id) throws SQLException {
        String qry = "SELECT * FROM client WHERE id =" + id;
        Client c = null;

        Connection connection = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(qry);
        if (resultSet.next()) {
            do {
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String password = resultSet.getString("decouvert");
                String mail = resultSet.getString("mail");
                c = new Client(id, nom, prenom, mail, password);
            }
            while (resultSet.next());
        }
        connection.close();
        statement.close();
        resultSet.close();
        return c;
    }

    public static Client getClientByMail(String email) throws SQLException {
        //liste de tout les boutiques
        String qry = "SELECT * FROM client WHERE client.mail = '" + email + "'";
        Client c = null;

        Connection connection = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(qry);
        if (resultSet.next()) {
            do {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String password = resultSet.getString("password");
                c = new Client(id, nom, prenom, email, password);
            }
            while (resultSet.next());
        }
        connection.close();
        statement.close();
        resultSet.close();
        return c;
    }

    public static boolean connectionClient(String email, String password) throws SQLException {
        String qry = "SELECT * FROM client WHERE client.mail = '" + email + "' AND client.password = '" + password + "'" ;
        Client c = null;
        boolean res = false;

        Connection connection = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(qry);
        if (resultSet.next()) {
            do {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String password1 = password;
                c = new Client(id, nom, prenom, email, password);
            }
            while (resultSet.next());
        }
        connection.close();
        statement.close();
        resultSet.close();
        if(c == null){
            return false;
        }else{
            return true;
        }
    }


    public static void ajouterClient(Client client) throws SQLException {

        if(connectionClient(client.getMail(), client.getPassword()) == false){
            Connection connection = createConnexion();
            PreparedStatement st = connection.prepareStatement("INSERT INTO client (nom, prenom, mail, password, solde) VALUES (?, ?, ?, ?, ?)") ;
            st.setString(1, client.getNom() );
            st.setString(2, client.getPrenom());
            st.setString(3, client.getMail());
            st.setString(4, client.getPassword());
            st.setDouble(5, 50);

            st.executeUpdate();
            st.close();
            connection.close();
        }
        else{
            System.out.println("Client déjà existant");
        }
    }
}
