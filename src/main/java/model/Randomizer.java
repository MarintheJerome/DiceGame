package model;

import java.util.Observable;

/**
 * Created by jerome on 26/01/2017.
 */
public class Randomizer extends Observable{
    private static Randomizer instance = null;

    private int randomValue;

    private Randomizer(){
        randomValue = 0;
    }

    public synchronized static Randomizer getInstance() {
        if(instance == null){
            instance = new Randomizer();
        }
        return instance;
    }

    public int getRandomValue(int min, int max){
        setRandomValue(min, max);
        return this.randomValue;
    }

    private void setRandomValue(int min, int max) {
        this.randomValue = ((int) (Math.random() * max)) + min;
        setChanged();
        notifyObservers(this.randomValue);
    }
}
