package model.game;

import java.util.Observable;

/**
 * Created by jerome on 26/01/2017.
 */
public class Randomizer extends Observable{
    public int randomValue;

    public Randomizer(){
        randomValue = 0;
    }

    public int getRandomValue(int min, int max){
        setRandomValue(min, max);
        return randomValue;
    }

    private void setRandomValue(int min, int max) {
        randomValue = ((int) (Math.random() * max)) + min;
        setChanged();
        notifyObservers();
    }
}
