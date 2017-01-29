package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.DiceGame;
import model.Entry;
import controller.persistence.BDD;
import controller.persistence.FactoryBDD;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by jerome on 22/01/2017.
 */
public class HighScoresController implements Initializable {

    @FXML
    public TableView<Entry> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(true);

        ArrayList<Entry> highScores = new ArrayList<>();
        BDD bdd = DiceGame.getInstance().getChoosenBDD();
        try {
             highScores = bdd.getHighScores();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TableColumn lastnameCol = new TableColumn("Nom");
        lastnameCol.setPrefWidth(90);
        lastnameCol.setCellValueFactory(new PropertyValueFactory<Entry,String>("lastname"));

        TableColumn firstnameCol = new TableColumn("Prénom");
        firstnameCol.setPrefWidth(90);
        firstnameCol.setCellValueFactory(new PropertyValueFactory<Entry,String>("firstname"));

        TableColumn scoreCol = new TableColumn("Score");
        scoreCol.setSortType(TableColumn.SortType.DESCENDING);
        scoreCol.setPrefWidth(70);
        scoreCol.setCellValueFactory(
                new PropertyValueFactory<Entry,Integer>("score")
        );

        TableColumn emailCol = new TableColumn("Date");
        emailCol.setPrefWidth(83);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Entry,Date>("date")
        );
        tableView.setItems(convertIntoObservableList(highScores));
        tableView.getColumns().addAll(lastnameCol, firstnameCol, scoreCol, emailCol);
        tableView.getSortOrder().add(scoreCol);
    }

    private ObservableList<Entry> convertIntoObservableList(ArrayList<Entry> arrayListEntries){
        ObservableList<Entry> entries = FXCollections.observableArrayList();
        for(Entry entry : arrayListEntries){
            entries.add(entry);
        }
        return entries;
    }
}
