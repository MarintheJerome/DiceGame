package model.game;

/**
 * Created by jerome on 06/01/2017.
 */
public class DiceGame {
    private static DiceGame instance = null;

    public synchronized static DiceGame getInstance() {
        if(instance == null){
            instance = new DiceGame();
        }
        return instance;
    }

    private DiceGame() {

    }
}
