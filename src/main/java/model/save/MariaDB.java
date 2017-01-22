package model.save;

import model.game.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by jerome on 21/01/2017.
 */
public class MariaDB implements BDD {

    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private final String DB_URL = "jdbc:mariadb://192.168.100.174/db";

    //  Database credentials
    private final String USER = "root";
    private final String PASS = "root";

    public MariaDB(){
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/DiceGame", "root", "root");
            System.out.println("Connected database successfully...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveGame() {

    }

    public Player insertPlayer() {
        return null;
    }
}
