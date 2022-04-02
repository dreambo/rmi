package libfront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modele.Livre;
import rmiInterface.LivreService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ArticleController {
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField tfTitre;

    @FXML
    private TextField tfPrix;

    @FXML
    private TextField tfQuantite;

    @FXML
    private TextArea tfDescription;

    @FXML
    private Label labErreur;

    @FXML
    void addArticle(ActionEvent event) throws IOException, NotBoundException, SQLException {
        LivreService livreService = (LivreService) Naming.lookup("rmi://localhost:5099/Librairie");
         livreService.ajouterLivre(new Livre(tfTitre.getText(), Double.parseDouble(tfPrix.getText()), Integer.parseInt(tfQuantite.getText()), tfDescription.getText()));
        //if(testAdd){
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
      //  } else {
        //    labErreur.setText("Erreur");
        //}

    }


    public void SwitchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}
