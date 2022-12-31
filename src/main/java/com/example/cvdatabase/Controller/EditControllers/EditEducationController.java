package com.example.cvdatabase.Controller.EditControllers;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Model.Education;
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

public class EditEducationController implements Initializable {


    Education education = new Education();
    int index = 0;
    @FXML
    private MFXGenericDialog rootPane;
    @FXML
    private MFXFontIcon minimizeIcon;
    @FXML
    private MFXFontIcon closeIcon;
    @FXML
    private MFXTextField nameField;
    @FXML
    private MFXDatePicker startDateField;
    @FXML
    private MFXDatePicker endDateField;
    @FXML
    private MFXButton editConfirmButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        for (int i = 0; i < Controller.rootPerson.getEducation().size(); i++) {
            if (Controller.rootPerson.getEducation().get(i).getName().equals(Controller.rootPersonEdit)) {
                education = Controller.rootPerson.getEducation().get(i);
                index = i;
            }
        }
        System.out.println(education.getName());

        nameField.setText(education.getName());
        startDateField.setText(education.getStartDate());
        endDateField.setText(education.getEndDate());

        editConfirmButton.setOnAction(actionEvent -> onEditConfirm(index));
    }

    private void onEditConfirm(int index) {
        Controller.rootPerson.getEducation().get(index).setName(nameField.getText());
        Controller.rootPerson.getEducation().get(index).setStartDate(startDateField.getText());
        Controller.rootPerson.getEducation().get(index).setEndDate(endDateField.getText());
        Controller.createAlert("Education edited successfully", "");
    }


}
