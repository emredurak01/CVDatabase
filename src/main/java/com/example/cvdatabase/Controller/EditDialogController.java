package com.example.cvdatabase.Controller;

import com.example.cvdatabase.Application;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DatabaseConnector;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    private Stage stage;

    public void setStage(Stage stage){

        this.stage = stage;

    }

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

        String q = "update person set name=?,surname=?,birthdate=?,email=?,phone=? where id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setString(1,person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getBirthdate());
            ps.setString(4, person.getEmail());
            ps.setString(5, person.getPhone());
            ps.setInt(6,person.getId());

            if(ps.executeUpdate() > 0){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("This CV is updated,successfully!");
                alert.showAndWait();
                FXMLLoader loader;
                loader = new FXMLLoader(Objects.requireNonNull(Application.class.getResource(Config.mainPath)));
                Parent root = loader.load();
                Controller a = loader.getController();
                Scene scene = new Scene(root);
                a.setStage(stage);
                stage.setScene(scene);
                stage.show();
                a.table.getItems().clear();
                a.table.getTableColumns().clear();
                a.createTable();
                editConfirmButton.getScene().getWindow().hide();

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("This CV is not updated!");
                alert.showAndWait();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
}
