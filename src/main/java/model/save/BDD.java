package model.save;

import model.game.Player;

/**
 * Created by jerome on 21/01/2017.
 */
public interface BDD {
    void saveGame();
    Player insertPlayer();
}
