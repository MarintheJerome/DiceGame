package controller;

import controller.persistence.Save;
import controller.persistence.SaveFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.DiceGame;
import model.Player;
import model.Randomizer;
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

    private static Stage gameWindow;

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
                    setChoosenBDD();

                    Player player = new Player(lastname.getText(), firstname.getText());

                    // Ouverture de la fenêtre de jeu
                    page = loader.load();

                    MainController controller = loader.getController();
                    controller.setPlayer(player);

                    gameWindow = new Stage();
                    gameWindow.setTitle("SUPER DICE GAME");
                    Scene scene = new Scene(page, 600, 400);
                    gameWindow.setScene(scene);
                    Vue.stagePrincipal.close();
                    gameWindow.showAndWait();
                } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setChoosenBDD() {
        SaveFactory factory = new SaveFactory();
        Save save = null;

        Randomizer randomizer = Randomizer.getInstance();
        int randomValue = randomizer.getRandomValue(1, 3);

        if(randomValue == 1){
            save = factory.getSave("MongoDB");
        }
        if(randomValue == 2){
            save = factory.getSave("File");
        }
        if(randomValue == 3){
            save = factory.getSave("MariaDB");
        }
        DiceGame.getInstance().setChoosenSave(save);
    }
}
