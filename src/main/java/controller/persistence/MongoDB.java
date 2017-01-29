package controller.persistence;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import model.game.Entry;
import model.game.Player;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jerome on 21/01/2017.
 */
public class MongoDB implements BDD {

    private MongoClient mc ;
    private MongoDatabase mdb ;
    private MongoCollection collection;

    public MongoDB(){
        mc = new MongoClient("localhost",27017);
        mdb = mc.getDatabase("DiceGame");
        System.out.println("CONNECTED TO MONGO WESH");
    }

    @Override
    public void saveGame(Player player, int score) throws SQLException {
        insertPlayer(player);
    }

    private void insertPlayer(Player player) {
        collection = mdb.getCollection("players");
        collection.insertOne(playerToDocument(player));
    }

    @Override
    public ArrayList<Entry> getHighScores() throws SQLException {
        return null;
    }

    private Document playerToDocument(Player player){
        Document db = new Document();
        db.put("lastname", player.getLastname());
        db.put("firstname", player.getFirstname());
        return db;
    }
}
