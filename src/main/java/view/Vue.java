package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by jerome on 19/01/2017.
 */
public class Vue extends Application{
    private static Scene scene;
    private static Stage stagePrincipal;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader preloader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/principale.fxml"));
        Parent root = preloader.load();
        scene = new Scene(root,600,400);
        scene.getStylesheets().clear();

        stage.setTitle("Super jeu de dès");
        stage.setScene(scene);
        stagePrincipal = stage;
        stagePrincipal.setResizable(true);
        stage.show();
    }
}
