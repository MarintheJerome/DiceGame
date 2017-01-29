package model;

import controller.persistence.BDD;

/**
 * Created by jerome on 06/01/2017.
 */
public class DiceGame {
    private static DiceGame instance = null;

    private BDD choosenBDD;

    public synchronized static DiceGame getInstance() {
        if(instance == null){
            instance = new DiceGame();
        }
        return instance;
    }

    private DiceGame() {

    }

    public void setChoosenBDD(BDD bdd){
        this.choosenBDD = bdd;
    }

    public BDD getChoosenBDD(){
        return this.choosenBDD;
    }
}
