package model.game;

import java.util.Observable;

/**
 * Created by jerome on 06/01/2017.
 */
public class Die extends Observable {
    private int faceValue;

    public Die(){
        faceValue = 1;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void roll(){
        faceValue = ((int) (Math.random() * 6)) + 1;
    }
}
