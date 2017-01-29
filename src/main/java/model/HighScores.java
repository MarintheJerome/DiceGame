package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 * Created by jerome on 06/01/2017.
 */
@XmlRootElement(name="HighScores")
@XmlAccessorType(XmlAccessType.FIELD)
public class HighScores {
    private static HighScores instance = null;

    @XmlElement
    private ArrayList<Entry> entry;

    public synchronized static HighScores getInstance() {
        if(instance == null){
            instance = new HighScores();
        }
        return instance;
    }

    private HighScores() {
        entry = new ArrayList<Entry>();
    }

    public void setEntries(ArrayList<Entry> entry )
    {
        this.entry = entry;
    }

    public ArrayList<Entry> getEntries()
    {
        return this.entry;
    }

    public void addEntry(Entry entry){
        this.entry.add(entry);
    }
}
