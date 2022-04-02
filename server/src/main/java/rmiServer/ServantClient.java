package rmiServer;

import dbConnect.ClientBDD;
import modele.Client;
import rmiInterface.ClientService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class ServantClient extends UnicastRemoteObject implements ClientService {
    public ServantClient() throws RemoteException {
        super();
    }

    @Override
    public Client getClientByMail(String mail) throws SQLException {
        return dbConnect.ClientBDD.getClientByMail(mail);
    }

    @Override
    public Client getClientByID(int id) throws SQLException {
        return dbConnect.ClientBDD.getClientID(id);
    }

    @Override
    public void ajouterClient(Client client) throws RemoteException, SQLException {
        ClientBDD.ajouterClient(client);
    }


    @Override
    public List<Client> getClients() throws SQLException {
        return dbConnect.ClientBDD.getClients();
    }

    @Override
    public Boolean connectionClient(String email, String password) throws RemoteException, SQLException {
        return dbConnect.ClientBDD.connectionClient(email, password);
    }
}
