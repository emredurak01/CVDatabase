package com.example.cvdatabase;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    Database database = new Database();
    @FXML
    private Button CVAddButton;
    @FXML
    private Button CVListButton;
    @FXML
    private Button CVRemoveButton;

    @FXML
    private void initialize() {
        CVAddButton.setOnAction(actionEvent -> onAddCV());
        CVListButton.setOnAction(actionEvent -> onListCV());
        CVRemoveButton.setOnAction(actionEvent -> onRemoveCV());

        System.out.println("asdsa");
    }

    private void onAddCV() {
        database.addPerson("Emre");
    }

    private void onListCV(){database.listPersons();}
    private void onRemoveCV(){database.removePersons();}





}