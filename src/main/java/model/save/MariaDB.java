package model.save;

import model.game.Player;
import java.sql.*;

/**
 * Created by jerome on 21/01/2017.
 */
public class MariaDB implements BDD {

    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private final String DB_URL = "jdbc:mariadb://192.168.100.174/db";

    //  Database credentials
    private final String USER = "root";
    private final String PASS = "root";

    private Connection connection;

    public MariaDB(){
        connection = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DiceGame", "root", "root");
            System.out.println("Connected database successfully...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveGame() {

    }

    public Player insertPlayer(String lastname, String firstname) throws SQLException {
        Player player = checkIfPlayerExists(lastname, firstname);
        if(player == null){
            PreparedStatement preparedStatement;
            String request = "INSERT INTO PLAYER(lastname, firstname) VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, lastname);
            preparedStatement.setString(2, firstname);
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Player(Math.toIntExact(generatedKeys.getLong(1)), lastname, firstname);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        }else{
            return player;
        }
    }

    public Player checkIfPlayerExists(String lastname, String firstname) throws SQLException {
        PreparedStatement preparedStatement;
        String request = "SELECT * FROM Player WHERE lastname = ? AND firstname = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, lastname);
        preparedStatement.setString(2, firstname);
        ResultSet result = preparedStatement.executeQuery();
        if(result.next()){
            return new Player(result.getInt("idPlayer"), result.getString("lastname"), result.getString("firstname"));
        }
        else{
            return null;
        }
    }
}
