package model.game;

/**
 * Created by jerome on 26/01/2017.
 */
public class SuperRandomizer {

    public static int MEGARANDOMIZER(int min, int max){
        return ((int) (Math.random() * max)) + min;
    }
}
