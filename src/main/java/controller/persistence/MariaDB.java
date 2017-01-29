package controller.persistence;

import model.Entry;
import model.Player;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by jerome on 21/01/2017.
 */
public class MariaDB implements Save {

    private final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    private final String DB_URL = "jdbc:mariadb://192.168.100.174/db";

    //  Database credentials
    private final String USER = "root";
    private final String PASS = "root";

    private Connection connection;

    public MariaDB(){
        connection = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/DiceGame", "root", "root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveGame(Player player, int score) throws SQLException {
        player = insertPlayer(player);
        insertHighScores(player, score);
    }

    private void insertHighScores(Player player, int score) throws SQLException {
        PreparedStatement preparedStatement;
        String request = "INSERT INTO HIGHSCORES(idPlayer, score, date) VALUES(?, ?, ?)";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, player.getId());
        preparedStatement.setInt(2, score);
        preparedStatement.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        preparedStatement.executeUpdate();
    }

    private Player insertPlayer(Player player) throws SQLException {
        Player playerToInsert = checkIfPlayerExists(player);
        if(playerToInsert == null) {
            PreparedStatement preparedStatement;
            String request = "INSERT INTO PLAYER(lastname, firstname) VALUES(?, ?)";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, player.getLastname());
            preparedStatement.setString(2, player.getFirstname());
            preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Player(Math.toIntExact(generatedKeys.getLong(1)), player.getLastname(), player.getFirstname());
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        }
        else{
            return playerToInsert;
        }
    }

    private Player checkIfPlayerExists(Player player) throws SQLException {
        PreparedStatement preparedStatement;
        String request = "SELECT * FROM Player WHERE lastname = ? AND firstname = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, player.getLastname());
        preparedStatement.setString(2, player.getFirstname());
        ResultSet result = preparedStatement.executeQuery();
        if(result.next()){
            return new Player(result.getInt("idPlayer"), result.getString("lastname"), result.getString("firstname"));
        }
        else{
            return null;
        }
    }

    @Override
    public ArrayList<Entry> getHighScores() throws SQLException {
        ArrayList<Entry> toReturn = new ArrayList<>();
        PreparedStatement preparedStatement;
        String request = "SELECT * FROM Highscores";
        preparedStatement = connection.prepareStatement(request);
        ResultSet result = preparedStatement.executeQuery();
        while(result.next()){
            int idPlayer = result.getInt("idPlayer");
            Player player = getPlayerFromId(idPlayer);
            toReturn.add(new Entry(player.getLastname(), player.getFirstname(), result.getInt("score"), result.getDate("date")));
        }
        return toReturn;
    }

    private Player getPlayerFromId(int idPlayer) throws SQLException {
        PreparedStatement preparedStatement;
        String request = "SELECT * FROM Player WHERE idPlayer = ?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, idPlayer);
        ResultSet result = preparedStatement.executeQuery();
        if(result.next()){
            return new Player(result.getInt("idPlayer"), result.getString("lastname"), result.getString("firstname"));
        }
        else{
            return null;
        }
    }
}
