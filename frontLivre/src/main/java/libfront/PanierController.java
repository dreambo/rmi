package libfront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Commande;
import modele.Livre;
import rmiInterface.ClientService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PanierController implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML
    private ListView<Livre> lvArticles;

    @FXML
    private Label mtPanier;


    public void SwitchToVitrine(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("vitrine-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void SwitchToCommande(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("paiement-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Commande articles = HelloController.getClient().getPanier();
        mtPanier.setText(String.valueOf(articles.getTotalPanier()));
        List<Livre> livres = articles.getPanier();
        lvArticles.getItems().addAll(livres);
    }
}
