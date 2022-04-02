package rmiServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ApplicationServer {
    public static void main(String[] args) throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(5099);

            registry.rebind("Librairie", new ServantLivre());

            registry.rebind("Client", new ServantClient());

            registry.rebind("Banque", new ServantBanque());
            System.out.println("Serveur prêt");
        }catch(Exception e){
            System.out.println("Serveur exception : " + e.toString());
        }
    }
}
