package model.save;

import model.game.Entry;
import model.game.Player;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jerome on 21/01/2017.
 */
public class FileSave implements BDD {
    public void saveGame() {

    }

    public Player insertPlayer(String lastname, String firtname) throws SQLException {
        return null;
    }

    public Player checkIfPlayerExists(String lastname, String firstname) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Entry> getHighScores() throws SQLException {
        return null;
    }
}
