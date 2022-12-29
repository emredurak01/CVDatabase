package com.example.cvdatabase;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.Database;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Application extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        DatabaseConnector.getInstance();

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(Config.mainPath));
        fxmlLoader.setControllerFactory(c -> new Controller(stage));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setTitle("CV DATABASE");
        stage.setScene(scene);
        stage.show();
    }
}