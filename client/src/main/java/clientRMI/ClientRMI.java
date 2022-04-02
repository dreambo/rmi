package clientRMI;

import dbConnect.BanqueBDD;
import dbConnect.LivreBDD;
import modele.*;
import rmiInterface.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ClientRMI {
    public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException, SQLException {

        LivreService livreService = (LivreService) Naming.lookup("rmi://localhost:5099/Librairie");
        ClientService clientService = (ClientService) Naming.lookup("rmi://localhost:5099/Client");
        BanqueService banqueService = (BanqueService) Naming.lookup("rmi://localhost:5099/Banque");

        List<Livre> livres = livreService.getLivres();
        livreService.ajouterLivre(new Livre("Trombone", 5, 25, "Histoire du Trombone"));
        System.out.println(livres);  //liste de tout les livres de la vitrine

        List<Client> clients = clientService.getClients();   //liste de tout les clients
        System.out.println(clients);
        clientService.ajouterClient(new Client("Jardin", "Elodie", "elodie.jardin@gmail.com", "admin1234"));
        System.out.println("La connection à la boutique a été effectué ? :  "+clientService.connectionClient("elodie.jardin@gmail.com", "admin1234"));  //retourne true car il existe, connection établie
        Client client = clientService.getClientByMail("sofiane.boudhaim@gmail.com");
        client.ajouterAuPanier(livreService.getLivreByID(8));
        client.ajouterAuPanier(livreService.getLivreByID(2));
        System.out.println(client.getPanier());
        System.out.println("Total du panier : " + client.getTotalPanier());
        System.out.println("La connection à la banque a été effectué ? :  "+banqueService.verifierConnexion("SofianeBoudhaim", "Admin123"));
        System.out.println("Solde présente sur le compte : "+BanqueBDD.getSolde("SofianeBoudhaim", "Admin123"));
        if(banqueService.verifierSolde("SofianeBoudhaim","Admin123", client.getTotalPanier())) {
            banqueService.payer("SofianeBoudhaim", "Admin123", client.getPanier());
            System.out.println("Solde après payement " + +BanqueBDD.getSolde("SofianeBoudhaim", "Admin123"));
        }else{
            System.out.println("Paiement impossible, solde insuffisante");
        }
    }
}
