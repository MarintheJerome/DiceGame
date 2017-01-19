package game;

import java.util.Observable;

/**
 * Created by jerome on 06/01/2017.
 */
public class Die extends Observable {
    public int faceValue = 1;

    public Die(){

    }

    public void roll(){
        faceValue = ((int)Math.random() * 6);
    }
}
