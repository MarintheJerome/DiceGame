package controller.persistence;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import model.game.Entry;
import model.game.Player;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jerome on 21/01/2017.
 */
public class MongoDB implements BDD {

    private MongoClient mc ;
    private MongoDatabase mdb ;
    private MongoCollection<Document> collection;

    public MongoDB(){
        mc = new MongoClient("localhost",27017);
        mdb = mc.getDatabase("DiceGame");
        collection = mdb.getCollection("entries");
    }

    @Override
    public void saveGame(Player player, int score) throws SQLException {
        Entry entry = new Entry(player.getLastname(), player.getFirstname(), score, new Date(System.currentTimeMillis()));
        insertEntry(entry);
        getHighScores();
    }

    private void insertEntry(Entry entry) {
        collection.insertOne(entryToDocument(entry));
    }

    @Override
    public ArrayList<Entry> getHighScores() throws SQLException {
        ArrayList<Entry> toReturn = new ArrayList<Entry>();
        FindIterable<Document> res = collection.find();
        for(Document document : res) {
            Entry entry = new Entry(document);
            toReturn.add(entry);
        }
        return toReturn;
    }

    private Document entryToDocument(Entry entry){
        Document doc = new Document();
        doc.put("lastname", entry.getLastname());
        doc.put("firstname", entry.getFirstname());
        doc.put("score", entry.getScore());
        doc.put("date", entry.getDate());
        return doc;
    }
}
