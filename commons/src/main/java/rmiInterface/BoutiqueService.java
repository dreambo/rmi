package rmiInterface;

import modele.Boutique;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface BoutiqueService extends Remote {
    List<Boutique> getBoutiques() throws RemoteException, SQLException;
    Boutique getBoutiqueById(int id) throws RemoteException, SQLException;
    Boutique getBoutiqueByNom(String name) throws RemoteException, SQLException;
}
