package model;

import controller.persistence.Save;

/**
 * Created by jerome on 06/01/2017.
 */
public class DiceGame {
    private static DiceGame instance = null;

    private Save choosenSave;

    public synchronized static DiceGame getInstance() {
        if(instance == null){
            instance = new DiceGame();
        }
        return instance;
    }

    private DiceGame() {

    }

    public void setChoosenSave(Save save){
        this.choosenSave = save;
    }

    public Save getChoosenSave(){
        return this.choosenSave;
    }
}
