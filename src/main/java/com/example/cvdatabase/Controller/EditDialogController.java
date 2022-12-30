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
        surnameField.setText(Controller.personListSelection.listIterator().next().getSurname());
        dateField.setText(Controller.personListSelection.listIterator().next().getBirthdate());
        emailField.setText(Controller.personListSelection.listIterator().next().getEmail());
        phoneField.setText(Controller.personListSelection.listIterator().next().getPhone());

        StringBuilder interestString = new StringBuilder();

        for(int i = 0; i < Controller.personListSelection.listIterator().next().getInterests().size(); i++) {
            interestString.append(Controller.personListSelection.listIterator().next().getInterests().get(i)).append(",");
        }
        interestString.setLength(interestString.length() - 1);

        StringBuilder skillString = new StringBuilder();
        for(int i = 0; i < Controller.personListSelection.listIterator().next().getSkills().size(); i++) {
            skillString.append(Controller.personListSelection.listIterator().next().getSkills().get(i)).append(",");
        }
        skillString.setLength(skillString.length() - 1);


        interestsField.setText(String.valueOf(interestString));
        skillsField.setText(String.valueOf(skillString));
        editConfirmButton.setOnAction(actionEvent -> onEditConfirm(Controller.personListSelection));
    }

    private void onEditConfirm(ObservableList<Person> personList){
        Person person;
        person = personList.listIterator().next();
        person.setName(nameField.getText());
        person.setSurname(surnameField.getText());
        person.setBirthdate(dateField.getText());
        person.setEmail(emailField.getText());
        person.setPhone(phoneField.getText());
    }
}
