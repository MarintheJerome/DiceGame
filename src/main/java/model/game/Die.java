package model.game;

import java.util.Observable;

/**
 * Created by jerome on 06/01/2017.
 */
public class Die extends Observable {
    public int faceValue;

    public Die(){
        faceValue = 1;
    }

    public int roll(){
        faceValue = ((int) (Math.random() * 6));
        return faceValue;
    }
}
