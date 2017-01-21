package model.game;

import java.util.Observable;

/**
 * Created by jerome on 06/01/2017.
 */
public class Player extends Observable {
    private int id;
    private String lastname;
    private String firstname;

    public Player(int id, String lastname, String firstname){
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
    }

    public int getId() {
        return id;
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
}
