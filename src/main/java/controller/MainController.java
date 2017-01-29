package controller;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DiceGame;
import model.Die;
import model.Player;
import controller.persistence.BDD;
import controller.persistence.FactoryBDD;
import model.Randomizer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by jerome on 19/01/2017.
 */
public class MainController implements Initializable{

    private final static int NUMBER_ROLL_AUTHORIZED = 10;

    @FXML
    public Button buttonHighScores, buttonRules, buttonDice, buttonReplay;

    @FXML
    public ImageView pictureDie1, pictureDie2;

    @FXML
    public TextField textFieldScore, textFieldDie1, textFieldDie2;

    @FXML
    public Label labelEndGame;

    private Die die;

    private Player player;

    private int nbRolls = 0;

    private Pane page = null;

    private static Stage gameWindow;

    public void initialize(URL location, ResourceBundle resources) {
        pictureDie1.setImage(new Image(getClass().getResourceAsStream("/dice/dice6.png")));
        pictureDie2.setImage(new Image(getClass().getResourceAsStream("/dice/dice6.png")));

        textFieldScore.setText("0");

        die = new Die();
    }

    @FXML
    public void rollDice() throws SQLException {
        nbRolls++;

        animateDes();
        die.roll();
        pictureDie1.setImage(getPictureFromRoll(die.getFaceValue()));
        textFieldDie1.setText(Integer.toString(die.getFaceValue()));

        int resultat = die.getFaceValue();

        die.roll();
        pictureDie2.setImage(getPictureFromRoll(die.getFaceValue()));
        textFieldDie2.setText(Integer.toString(die.getFaceValue()));

        resultat += die.getFaceValue();
        if(resultat == 7){
            int currentScore = Integer.parseInt(textFieldScore.getText()) + 10;
            textFieldScore.setText(Integer.toString(currentScore));
        }

        // end game, ajouter tableau des scores
        if(nbRolls == NUMBER_ROLL_AUTHORIZED){
            buttonDice.setDisable(true);
            buttonReplay.setVisible(true);
            labelEndGame.setVisible(true);

            saveGame();
        }
    }

    private void saveGame() throws SQLException {
        FactoryBDD factory = new FactoryBDD();

        BDD mariaDB;
        BDD mongoDB;
        BDD file;

        file = factory.getBDD("File");
        file.saveGame(player, Integer.parseInt(textFieldScore.getText()));

        mariaDB = factory.getBDD("MariaDB");
        mariaDB.saveGame(player, Integer.parseInt(textFieldScore.getText()));

        mongoDB = factory.getBDD("MongoDB");
        mongoDB.saveGame(player, Integer.parseInt(textFieldScore.getText()));
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

    @FXML
    public void rules() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/rules.fxml"));
        // Ouverture de la fenêtre de jeu
        page = (Pane) loader.load();

        gameWindow = new Stage();
        gameWindow.setTitle("SUPER REGLES");
        Scene scene = new Scene(page, 386, 218);
        gameWindow.setScene(scene);
        gameWindow.showAndWait();
    }

    @FXML
    public void highScores() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/scores.fxml"));
        // Ouverture de la fenêtre de jeu
        page = (Pane) loader.load();

        gameWindow = new Stage();
        gameWindow.setTitle("SUPER TABLEAU DES SCORES");
        Scene scene = new Scene(page, 500, 350);
        gameWindow.setScene(scene);
        gameWindow.showAndWait();
    }

    @FXML
    public void replay(){
        buttonDice.setDisable(false);
        buttonReplay.setVisible(false);
        labelEndGame.setVisible(false);
        textFieldDie1.setText("");
        textFieldDie2.setText("");
        textFieldScore.setText("0");
        nbRolls = 0;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
