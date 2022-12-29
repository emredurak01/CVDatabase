package com.example.cvdatabase.Controller;


import com.example.cvdatabase.Application;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DataManager;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import com.example.cvdatabase.Model.Person;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.collections.FXCollections;
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
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class DialogController implements Initializable {

    Controller controller = new Controller();
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

    private Stage previousStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        addConfirmButton.setOnAction(actionEvent -> onAddConfirm());
    }


    private void onAddConfirm() {

        controller.createPerson(nameField.getText(), surnameField.getText(), dateField.getText(), emailField.getText(), phoneField.getText()
                , interestsField.getText(), skillsField.getText());

        String insert = "insert into Person(name,surname,birthdate,email,phone,interests,skills) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(insert);

            ps.setString(1, nameField.getText());
            ps.setString(2, surnameField.getText());
            ps.setString(3, dateField.getText());
            ps.setString(4, emailField.getText());
            ps.setString(5, phoneField.getText());
            ps.setString(6, interestsField.getText());
            ps.setString(7, skillsField.getText());

            if (ps.executeUpdate() > 0) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setContentText("This CV is created,successfully!");
                alert.showAndWait();
                FXMLLoader loader;
                loader = new FXMLLoader(Objects.requireNonNull(Application.class.getResource(Config.mainPath)));
                Parent root = loader.load();
                Controller a = loader.getController();
                System.out.println(a.personList.toString());
                a.createTable();
                System.out.println(a.table.getItems().size());
                a.table.update();

            } else {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("This CV is not created!");
                alert.showAndWait();

            }


        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }


}