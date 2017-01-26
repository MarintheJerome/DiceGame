package model.game;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by jerome on 06/01/2017.
 */
public class Die implements Observer {
    private Randomizer randomizer;
    private int faceValue;

    public Die(){
        faceValue = 1;
        randomizer = new Randomizer();
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void roll(){
        faceValue = randomizer.getRandomValue(1, 6);
    }

    @Override
    public void update(Observable o, Object arg) {
        Randomizer r = (Randomizer) o;
        faceValue = r.getRandomValue(1, 6);
    }
}
