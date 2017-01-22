package model.save;

import model.game.Player;

import java.sql.SQLException;

/**
 * Created by jerome on 21/01/2017.
 */
public interface BDD {
    void saveGame();
    Player insertPlayer(String lastname, String firtname) throws SQLException;
    Player checkIfPlayerExists(String lastname, String firstname) throws SQLException;
}
