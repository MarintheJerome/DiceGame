package controller;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.game.Die;
import model.game.Player;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jerome on 19/01/2017.
 */
public class MainController implements Initializable {

    private final static int NUMBER_ROLL_AUTHORIZED = 10;

    @FXML
    public Button buttonHighScores, buttonSave, buttonRules, buttonDice;

    @FXML
    public ImageView pictureDie1, pictureDie2;

    @FXML
    public TextField textFieldScore, textFieldDie1, textFieldDie2;

    public Die die1, die2;

    public Player player;

    public int nbRolls = 0;

    public void initialize(URL location, ResourceBundle resources) {
        pictureDie1.setImage(new Image(getClass().getResourceAsStream("/dice/dice6.png")));
        pictureDie2.setImage(new Image(getClass().getResourceAsStream("/dice/dice6.png")));

        textFieldScore.setText("0");

        die1 = new Die();
        die2 = new Die();
    }

    @FXML
    public void rollDice(){
        nbRolls++;

        animateDes();
        die1.roll();
        die2.roll();

        pictureDie1.setImage(getPictureFromRoll(die1.getFaceValue()));
        pictureDie2.setImage(getPictureFromRoll(die2.getFaceValue()));

        textFieldDie1.setText(Integer.toString(die1.getFaceValue()));
        textFieldDie2.setText(Integer.toString(die2.getFaceValue()));

        int resultat = die1.getFaceValue() + die2.getFaceValue();
        if(resultat == 7){
            int currentScore = Integer.parseInt(textFieldScore.getText()) + 10;
            textFieldScore.setText(Integer.toString(currentScore));
        }

        // end game, ajouter tableau des scores
        if(nbRolls == NUMBER_ROLL_AUTHORIZED){

        }
    }

    private void animateDes() {
        RotateTransition rt1 = new RotateTransition(Duration.millis(1000), pictureDie1);
        RotateTransition rt2 = new RotateTransition(Duration.millis(1000), pictureDie2);
        rt1.setByAngle(180*6);
        rt2.setByAngle(180*6);
        rt1.play();
        rt2.play();
    }

    private Image getPictureFromRoll(int rollValue){
        switch(rollValue){
            case 1:
                return new Image(getClass().getResourceAsStream("/dice/dice1.png"));
            case 2:
                return new Image(getClass().getResourceAsStream("/dice/dice2.png"));
            case 3:
                return new Image(getClass().getResourceAsStream("/dice/dice3.png"));
            case 4:
                return new Image(getClass().getResourceAsStream("/dice/dice4.png"));
            case 5:
                return new Image(getClass().getResourceAsStream("/dice/dice5.png"));
            case 6:
                return new Image(getClass().getResourceAsStream("/dice/dice6.png"));
            default: // should never go here
                   return null;
        }
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
