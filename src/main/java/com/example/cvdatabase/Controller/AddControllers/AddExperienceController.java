package com.example.cvdatabase.Controller.AddControllers;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import com.example.cvdatabase.Model.Experience;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddExperienceController implements Initializable {

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
    private MFXButton addConfirmButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
        addConfirmButton.setOnAction(actionEvent -> onAddConfirm());
    }

    private void onAddConfirm() {
        Experience newExperience = new Experience(titleField.getText(), startDateField.getText(), endDateField.getText());
        Controller.rootPerson.getExperiences().add(newExperience);
        Controller.createAlert("Experience created successfully.", "");

        String q = "insert into Experience(person_id,title,start_date,end_date) values(?,?,?,?)";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1,Controller.rootPerson.getId());
            ps.setString(2, newExperience.getTitle());
            ps.setString(3, newExperience.getStartDate());
            ps.setString(4, newExperience.getEndDate());

            if(ps.executeUpdate() > 0){

                Controller.createAlert("Experience data is created for the selected CV.","");

            }else{

                Controller.createAlert("An error occurred.","Error");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}