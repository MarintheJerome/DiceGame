package game;

/**
 * Created by jerome on 06/01/2017.
 */
public class HighScore {
    private static HighScore instance = null;

    public synchronized static HighScore getInstance() {
        if(instance == null){
            instance = new HighScore();
        }
        return instance;
    }

    private HighScore() {

    }
}
