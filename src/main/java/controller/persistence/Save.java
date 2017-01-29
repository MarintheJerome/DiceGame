package controller.persistence;

import model.Entry;
import model.Player;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jerome on 21/01/2017.
 */
public interface Save {
    void saveGame(Player player, int score) throws SQLException;
    ArrayList<Entry> getHighScores() throws SQLException;
}
