package com.example.cvdatabase;


import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class DialogController implements Initializable {


    @FXML
    private MFXGenericDialog rootPane;
    @FXML
    private MFXButton addConfirmButton;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXDatePicker dateField;

    @FXML
    private MFXTextField educationsField;

    @FXML
    private MFXTextField emailField;

    @FXML
    private MFXTextField experiencesField;

    @FXML
    private MFXTextField interestsField;

    @FXML
    private MFXFontIcon minimizeIcon;

    @FXML
    private MFXTextField nameField;

    @FXML
    private MFXTextField phoneField;

    @FXML
    private MFXTextField publicationsField;

    @FXML
    private MFXTextField skillsField;

    @FXML
    private MFXTextField surnameField;

    @FXML
    private MFXTextField tagsField;

    @FXML
    private HBox windowHeader;

    private String name;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        addConfirmButton.setOnAction(actionEvent -> onAddConfirm());
    }

    private void onAddConfirm() {
        name = nameField.getText();
        System.out.println(name);
    }


}
