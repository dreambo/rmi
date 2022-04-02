package libfront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Client;
import modele.Commande;
import rmiInterface.BanqueService;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PaimentController implements Initializable {
    private Stage stage;
    private Scene scene;
    @FXML
    private TextField inputPass;

    @FXML
    private Label retourFalse;

    @FXML
    private Label mtAchat;

    @FXML
    private TextField inputName;

    Client client = HelloController.getClient();
    Commande commande = HelloController.getClient().getPanier();

    @FXML
    void ControlPaiement(ActionEvent event) throws IOException, NotBoundException, SQLException {
        BanqueService banqueService = (BanqueService) Naming.lookup("rmi://localhost:5099/Banque");
        boolean banqueConnect = banqueService.verifierConnexion(inputName.getText(), inputPass.getText());
        System.out.println(banqueConnect);
        if (banqueConnect){
            boolean testSolde = banqueService.verifierSolde(inputName.getText(), inputPass.getText(), Double.parseDouble(mtAchat.getText()));
            System.out.println();
            if (testSolde){
                banqueService.payer(inputName.getText(), inputPass.getText(), commande);
                Parent root = FXMLLoader.load(getClass().getResource("command-view.fxml"));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                retourFalse.setText("Solde insufissant");
            }
        }else {
            retourFalse.setText("Connexion refusée");
        }
    }

    @FXML
    void SwitchToPanier(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("panier-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       inputName.setText(client.getPrenom()+client.getNom());
       mtAchat.setText(String.valueOf(client.getPanier().getTotalPanier()));

    }
}
