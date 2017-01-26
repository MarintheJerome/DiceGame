package controller.persistence;

import model.game.Entry;
import model.game.Player;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jerome on 21/01/2017.
 */
public interface BDD {
    void saveGame(Player player) throws SQLException;
    ArrayList<Entry> getHighScores() throws SQLException;
}
