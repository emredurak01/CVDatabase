package com.example.cvdatabase.Controller.AddControllers;


import com.example.cvdatabase.Controller.Controller;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HelpController implements Initializable {
    @FXML
    private MFXGenericDialog rootPane;
    @FXML
    private MFXFontIcon minimizeIcon;
    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXButton addHelpButton;
    @FXML
    private MFXButton removeHelpButton;
    @FXML
    private MFXButton editHelpButton;
    @FXML
    private MFXButton exportHelpButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        addHelpButton.setOnAction(actionEvent -> onAddHelp());
        removeHelpButton.setOnAction(actionEvent -> onRemoveHelp());
        editHelpButton.setOnAction(actionEvent -> onEditHelp());
        exportHelpButton.setOnAction(actionEvent -> onExportHelp());
    }

    private void onAddHelp() {
        Controller.createAlert("To add a CV to the application, click the \"Add\" button on the left side of the application. " + "This will open a new window with text fields for you to enter information about the CV, such as name, surname, date of birth, email, and phone. " + "Fill out the text fields with the appropriate information and click the \"Add\" button at the bottom of the window to add the CV to the table. " + "To add additional information to the CV, such as education, experience, publications, interests, skills, or tags, click on the corresponding header in the tree view on the right side of the application. " + "Then click the \"Add\" button again to open a window where you can enter the information. Fill out the text fields with the appropriate information and click \"Add\" to save the information to the CV. " + "To add more than one interest, skill, or tag at the same time, names can be separated with a comma (ex. tag1,tag2).", "Adding a CV");
    }

    private void onRemoveHelp() {
        Controller.createAlert("To remove a CV from the application, locate the CV in the table and click the \"Remove\" button on the left sidebar. " + "This will delete the entire CV, including all of the additional information. " + "If you only want to remove a single piece of information, such as an education entry or a skill, click on the corresponding item in the tree view and click the \"Remove\" button again. " + "This will remove that specific piece of information from the CV.", "Removing a CV");
    }

    private void onEditHelp() {
        Controller.createAlert("To edit a CV in the application, select the CV you want to edit in the table and click the \"Edit\" button on the left side of the application. " + "This will open a new window with the text fields pre-populated with the current information for the CV. " + "Edit the information in the text fields as desired and click the \"Edit\" button at the bottom of the window to save the changes to the CV. " + "To edit additional information for the CV, such as education, experience, publications, interests, or skills, click on the corresponding item in the tree view on the right side of the application and click the \"Edit\" button again. " + "A new window will appear with the text fields pre-populated with the current information. Edit the information as desired and click \"Edit\" to save the changes.", "Edit a CV");
    }

    private void onExportHelp() {
        Controller.createAlert("", "Exporting a CV");
    }
}
