package libfront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import modele.Client;
import modele.Commande;
import modele.Livre;
import rmiInterface.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class VitrineController implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField inputArticleID;

    @FXML
    private ListView<String> lvPanier;

    @FXML
    private ListView<Livre> lvLivres;
    String ret = null;
    LivreService livreService = (LivreService) Naming.lookup("rmi://localhost:5099/Librairie");

    @FXML
    private TextField inputname;
    Commande commande = HelloController.getClient().getPanier();
    Client client = HelloController.getClient();

    public VitrineController() throws MalformedURLException, NotBoundException, RemoteException {
    }

    @FXML
    void ajoutPanier(ActionEvent event) throws MalformedURLException, NotBoundException, RemoteException, SQLException {
        //LivreService livreService = (LivreService) Naming.lookup("rmi://localhost:5099/Librairie");
        int add = Integer.parseInt(inputArticleID.getText());
        this.client.ajouterAuPanier(livreService.getLivreByID(add));

        System.out.println(HelloController.getClient().getPanier());
        for (Livre livre : commande.getPanier()){
            ret = livre.getId()+" "+livre.getTitre();
        }
        lvPanier.getItems().addAll(ret);
    }


    @FXML
    void supprimePanier(ActionEvent event) throws MalformedURLException, NotBoundException, RemoteException, SQLException {
        int add = Integer.parseInt(inputArticleID.getText());
        this.client.supprimerDuPanier(livreService.getLivreByID(add));
        System.out.println(HelloController.getClient().getPanier());
    }

    public void SwitchToPanier(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("panier-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resource) {
        try {
            inputname.setText( HelloController.getClient().getMail());
         //   LivreService livreService = (LivreService) Naming.lookup("rmi://localhost:5099/Librairie");
            List<Livre> livres = livreService.getLivres();
            for (Livre l :livres){
                lvLivres.getItems().add(l);
            }
            for (Livre livre : commande.getPanier()){
                ret = livre.getId()+" "+livre.getTitre();
                lvPanier.getItems().addAll(ret);
            }
        } catch (SQLException | RemoteException e) {
            e.printStackTrace();
        }

    }
}
