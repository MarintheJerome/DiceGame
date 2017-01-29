package controller.persistence;

import model.game.Entry;
import model.game.HighScores;
import model.game.Player;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by jerome on 21/01/2017.
 */
public class FileSave implements BDD {

    private HighScores highScores;

    public FileSave(){
        highScores = HighScores.getInstance();
    }

    public void saveGame(Player player, int score) {
        readCurrentXml();
        saveInXml(player, score);
    }

    private void readCurrentXml() {
        File file = new File("highscores.xml");
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(HighScores.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            HighScores highScoresFile = (HighScores) jaxbUnmarshaller.unmarshal(file);
            for(Entry entry : highScoresFile.getEntries()){
                highScores.addEntry(entry);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private void saveInXml(Player player, int score) {
        highScores.addEntry(new Entry(player.getLastname(), player.getFirstname(), score, new Date(System.currentTimeMillis())));
        JAXBContext jaxbContext = null;
        try {
            jaxbContext = JAXBContext.newInstance(HighScores.class );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        Marshaller jaxbMarshaller = null;
        try {
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty( Marshaller.JAXB_FORMATTED_OUTPUT, true );
            jaxbMarshaller.marshal(highScores, new File( "highscores.xml" ) );
            jaxbMarshaller.marshal(highScores, System.out );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Entry> getHighScores() throws SQLException {
        readCurrentXml();
        return highScores.getEntries();
    }
}
