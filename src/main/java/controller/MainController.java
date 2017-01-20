package controller;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jerome on 19/01/2017.
 */
public class MainController implements Initializable {

    @FXML
    public Button buttonHighScores, buttonSave, buttonRules, buttonDice;

    @FXML
    public ImageView dice1, dice2;

    public void initialize(URL location, ResourceBundle resources) {
        dice1.setImage(new Image(getClass().getResourceAsStream("/dice/dice6.png")));
        dice2.setImage(new Image(getClass().getResourceAsStream("/dice/dice6.png")));
    }

    @FXML
    public void lancerDes(){
        animateDes();
    }

    private void animateDes() {
        RotateTransition rt1 = new RotateTransition(Duration.millis(1000), dice1);
        RotateTransition rt2 = new RotateTransition(Duration.millis(1000), dice2);
        rt1.setByAngle(180*6);
        rt2.setByAngle(180*6);
        rt1.play();
        rt2.play();
    }
}
