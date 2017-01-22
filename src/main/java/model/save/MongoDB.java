package model.save;

import model.game.Player;

import java.sql.SQLException;

/**
 * Created by jerome on 21/01/2017.
 */
public class MongoDB implements BDD {
    public void saveGame() {

    }

    public Player insertPlayer(String lastname, String firtname) throws SQLException {
        return null;
    }

    public Player checkIfPlayerExists(String lastname, String firstname) throws SQLException {
        return null;
    }

    public Player insertPlayer() {
        return null;
    }
}
