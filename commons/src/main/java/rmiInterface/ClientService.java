package rmiInterface;

import modele.Client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

/**
 * interface de connexion
 */
public interface ClientService extends Remote {
    List<Client> getClients() throws  RemoteException, SQLException;
    Client getClientByMail(String mail) throws  RemoteException, SQLException;
    Client getClientByID(int id) throws  RemoteException, SQLException;
    //ajouter client
    void ajouterClient(Client client) throws  RemoteException, SQLException;
    //connection client
    Boolean connectionClient(String email, String password) throws  RemoteException, SQLException;
}
