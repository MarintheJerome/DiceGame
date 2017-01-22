package model.game;

import java.sql.Date;

/**
 * Created by jerome on 06/01/2017.
 */
public class Entry {
    private String lastname;
    private String firstname;
    private int score;
    private Date date;

    public Entry(String lastname, String firstname, int score, Date date) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.score = score;
        this.date = date;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}