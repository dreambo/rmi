package libfront;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modele.Client;
import modele.Commande;
import modele.Livre;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class CommandController implements Initializable {
    private Stage stage;
    private Scene scene;
    Date today = new Date();
    DateFormat dform = new SimpleDateFormat("dd/MM/yy");
    @FXML
    private Text nowdate;
    @FXML
    private ListView<Livre> lvArticles;
    @FXML
    private Text mtAchat;
    public void SwitchToStart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String strDate = "Commande du "+dform.format(today);
        Commande client = HelloController.getClient().getPanier();
        List<Livre> livres = client.getPanier();
        System.out.println(strDate);
        lvArticles.getItems().addAll(livres);
        mtAchat.setText(String.valueOf(client.getTotalPanier()));
       // nowdate.setText(strDate);
    }

}
