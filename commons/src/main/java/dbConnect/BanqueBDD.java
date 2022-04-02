package dbConnect;

import modele.Client;
import modele.Commande;
import modele.Livre;

import java.sql.*;

import static dbConnect.Connexion.createConnexion;

public class BanqueBDD {

    public static boolean getConnectionBanque(String identifiant, String password) throws SQLException {

        String qry = "SELECT * FROM banque WHERE codeIdentifiant = '" + identifiant + "' AND password = '" + password + "'";
        String identifiant2 = "";
        Connection connection = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(qry);
        if (resultSet.next()) {
            do {
                identifiant2 = resultSet.getString("codeIdentifiant");
            }
            while (resultSet.next());
        }
        connection.close();
        statement.close();
        resultSet.close();

        if (!identifiant2.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean soldeSuffisante(String identifiant, String password, double cout) throws SQLException {
        String qry = "SELECT * FROM banque WHERE codeIdentifiant = '" + identifiant + "' AND password = '" + password + "'";
        Client c = null;
        double soldeBanque = 0;
        Connection connection = createConnexion();
        Statement statement = null;
        ResultSet resultSet = null;

        statement = connection.createStatement();
        resultSet = statement.executeQuery(qry);
        if (resultSet.next()) {
            do {
                soldeBanque = resultSet.getDouble("solde");
            }
            while (resultSet.next());
        }
        connection.close();
        statement.close();
        resultSet.close();
        if (soldeBanque >= cout) {
            return true;
        } else {
            return false;
        }
    }

    public static double getSolde(String identifiant, String password) throws SQLException {
        double soldeBanque = 0;
        if (getConnectionBanque(identifiant, password)) {
            String qry = "SELECT * FROM banque WHERE codeIdentifiant = '" + identifiant + "' AND password = '" + password + "'";


            Connection connection = createConnexion();
            Statement statement = null;
            ResultSet resultSet = null;

            statement = connection.createStatement();
            resultSet = statement.executeQuery(qry);
            if (resultSet.next()) {
                do {
                    soldeBanque = resultSet.getDouble("solde");
                }
                while (resultSet.next());
            }
            connection.close();
            statement.close();
            resultSet.close();
        }

        return soldeBanque;
    }

    public static boolean payer(String identifiant, String password, Commande panier) throws SQLException {
        double cout = panier.getTotalPanier();
        String qry = "UPDATE banque SET solde = ? WHERE codeIdentifiant = ? AND password = ?";
        if (getConnectionBanque(identifiant, password) == true) {
            Connection connection = createConnexion();
            PreparedStatement st = connection.prepareStatement("UPDATE banque SET solde = ? WHERE codeIdentifiant = ? AND password = ?");
            st.setDouble(1,  getSolde(identifiant,password)- cout);
            st.setString(2, identifiant);
            st.setString(3, password);

            st.executeUpdate();
            st.close();
            connection.close();
        } else {
            System.out.println("Paiement impossible");
        }

        for (Livre l : panier.getPanier()) {
            LivreBDD.baisserQteLivreByID(l.getId());
        }
        return false;
    }
}
