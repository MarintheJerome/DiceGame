package model.game;

import java.util.ArrayList;

/**
 * Created by jerome on 06/01/2017.
 */
public class HighScore {
    private static HighScore instance = null;
    private ArrayList<Entry> entries;

    public synchronized static HighScore getInstance() {
        if(instance == null){
            instance = new HighScore();
        }
        return instance;
    }

    private HighScore() {
        entries = new ArrayList<Entry>();
    }

    public void addEntry(Entry entry){
        entries.add(entry);
    }
}
