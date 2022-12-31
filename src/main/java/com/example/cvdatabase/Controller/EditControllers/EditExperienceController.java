package com.example.cvdatabase.Controller.EditControllers;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Model.Experience;
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
import java.util.ResourceBundle;

public class EditExperienceController implements Initializable {


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

    Experience experience = new Experience();
    int index = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        for (int i = 0; i < Controller.rootPerson.getExperiences().size(); i++) {
            if (Controller.rootPerson.getExperiences().get(i).getTitle().equals(Controller.rootPersonEdit)) {
                experience = Controller.rootPerson.getExperiences().get(i);
                index = i;
            }
        }
        System.out.println(index);

        titleField.setText(String.valueOf(experience.getTitle()));
        startDateField.setText(experience.getStartDate());
        endDateField.setText(experience.getEndDate());

        editConfirmButton.setOnAction(actionEvent -> onEditConfirm(index));
    }

    private void onEditConfirm(int index) {
        Controller.rootPerson.getExperiences().get(index).setTitle(titleField.getText());
        Controller.rootPerson.getExperiences().get(index).setStartDate(startDateField.getText());
        Controller.rootPerson.getExperiences().get(index).setEndDate(endDateField.getText());
        Controller.createAlert("Experience edited successfully", "");
    }


}
