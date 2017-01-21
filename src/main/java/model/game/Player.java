package model.game;

import java.util.Observable;

/**
 * Created by jerome on 06/01/2017.
 */
public class Player extends Observable {
    public String name;
    public int score;

    public Player(String name){
        this.name = name;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Lancer les deux dès
    public void roll(){

    }
}
