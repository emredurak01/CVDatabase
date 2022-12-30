package com.example.cvdatabase.Controller;

import com.example.cvdatabase.Application;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Model.Person;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditDialogController implements Initializable {

    Controller controller = new Controller();
    @FXML
    private MFXGenericDialog rootPane;
    @FXML
    private MFXButton editConfirmButton;

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

    private Stage previousStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));



        nameField.setText(Controller.personListSelection.listIterator().next().getName());
        //editConfirmButton.setOnAction(actionEvent -> onEditConfirm(personList));
    }

    private void onEditConfirm(ObservableList<Person> personList){
        System.out.println();
    }
}
