package com.example.cvdatabase.Controller.EditControllers;

import com.example.cvdatabase.Application;
import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DataManager;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import com.example.cvdatabase.Model.Experience;
import com.example.cvdatabase.Model.Person;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditExperienceController implements Initializable {


    Experience experience = new Experience();
    int index = 0;
    @FXML
    private MFXGenericDialog rootPane;
    @FXML
    private MFXFontIcon minimizeIcon;
    @FXML
    private MFXFontIcon closeIcon;
    @FXML
    private MFXTextField titleField;
    @FXML
    private MFXDatePicker startDateField;
    @FXML
    private MFXDatePicker endDateField;
    @FXML
    private MFXButton editConfirmButton;
    private Stage stage;

    public void setStage(Stage stage) {

        this.stage = stage;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        for (int i = 0; i < Controller.rootPerson.getExperiences().size(); i++) {
            if (Controller.rootPerson.getExperiences().get(i).getTitle().equals(Controller.rootPersonEdit.toString())) {
                experience = DataManager.getInstance().PullExperiences(Controller.rootPerson.getId()).get(i);
                System.out.println(Controller.rootPersonEdit);
                index = i;
            }
        }

        titleField.setText(String.valueOf(experience.getTitle()));
        startDateField.setText(experience.getStartDate());
        endDateField.setText(experience.getEndDate());

        editConfirmButton.setOnAction(actionEvent -> onEditConfirm(index));
    }

    private void onEditConfirm(int index) {

        String q = "update Experience set title=?,start_date=?,end_date=? where person_id = ? and id=?";


        try {
            Person person = Controller.rootPerson;


            person.getExperiences().get(index).setTitle(titleField.getText());
            person.getExperiences().get(index).setStartDate(startDateField.getText());
            person.getExperiences().get(index).setEndDate(endDateField.getText());

            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setString(1, experience.getTitle());
            ps.setString(2, experience.getStartDate());
            ps.setString(3, experience.getEndDate());
            ps.setInt(4, person.getId());
            ps.setInt(5, experience.getId());

            System.out.println(person.getName() + " " + person.getId() + " " + experience.getId());

            if (ps.executeUpdate() > 0) {

                FXMLLoader loader;
                loader = new FXMLLoader(Objects.requireNonNull(Application.class.getResource(Config.mainPath)));
                Parent root = loader.load();
                Controller a = loader.getController();
                Scene scene = new Scene(root);
                a.setStage(stage);
                stage.setScene(scene);
                stage.show();
                editConfirmButton.getScene().getWindow().hide();
                a.handleRowSelection();
                Controller.createAlert("Experience edited successfully.", "");


            } else {

                Controller.createAlert("Experience could not be edited.", "");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }


}
