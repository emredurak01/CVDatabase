package com.example.cvdatabase;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
    Database database = new Database();


    @FXML
    private void initialize() {



    }

    private void onAddCV() {
        database.addPerson("Emre");
    }

    private void onListCV(){database.listPersons();}
    private void onRemoveCV(){database.removePersons();}





}