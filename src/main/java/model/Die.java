package model;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jerome on 06/01/2017.
 */
public class Die implements Observer {
    private Randomizer randomizer;

    private int faceValue;

    public Die(){
        this.randomizer = Randomizer.getInstance();
        this.randomizer.addObserver(this);
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void roll(){
        this.randomizer.getRandomValue(1, 6);
    }

    @Override
    public void update(Observable o, Object arg) {
        this.faceValue = (int) arg;
    }
}
