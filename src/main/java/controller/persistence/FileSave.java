package controller.persistence;

import model.game.Entry;
import model.game.Player;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jerome on 21/01/2017.
 */
public class FileSave implements BDD {

    public void saveGame(Player player, int score) {

    }

    @Override
    public ArrayList<Entry> getHighScores() throws SQLException {
        return null;
    }
}
