package com.example.cvdatabase;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    Database database = new Database();
    @FXML
    private Button CVAddButton;

    @FXML
    private void initialize() {
        CVAddButton.setOnAction(actionEvent -> onAddCV());
    }

    private void onAddCV() {
        database.addPerson("Emre");
    }


}