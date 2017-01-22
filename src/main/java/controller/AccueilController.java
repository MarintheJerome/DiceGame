package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.game.Player;
import model.save.FactoryBDD;
import model.save.BDD;
import view.Vue;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jerome on 21/01/2017.
 */
public class AccueilController implements Initializable {

    @FXML
    public TextField lastname, firstname;

    @FXML
    public Label errorMessage;

    private Pane page = null;

    public static Stage gameWindow;

    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void play(){
        if(lastname.getText() == null || lastname.getText().trim().equals("") || firstname.getText() == null || firstname.getText().trim().equals("")){
            errorMessage.setVisible(true);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/principale.fxml"));
            try {
                Player player = insertOrGetPlayer(lastname.getText(), firstname.getText());

                // Ouverture de la fenêtre de jeu
                page = (Pane) loader.load();

                MainController controller = loader.getController();
                controller.setPlayer(player);

                gameWindow = new Stage();
                gameWindow.setTitle("Les Colons de Catanes");
                Scene scene = new Scene(page, 600, 400);
                gameWindow.setScene(scene);
                gameWindow.setMaximized(true);
                Vue.stagePrincipal.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private Player insertOrGetPlayer(String text, String text1) {
        FactoryBDD factory = new FactoryBDD();
        BDD mariaDB = factory.getBDD("MariaDB");
        return mariaDB.insertPlayer();
    }
}
