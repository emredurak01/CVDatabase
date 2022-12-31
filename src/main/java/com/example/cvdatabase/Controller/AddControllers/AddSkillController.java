package com.example.cvdatabase.Controller.AddControllers;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import io.github.palexdev.materialfx.controls.MFXButton;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddSkillController implements Initializable {

    @FXML
    private MFXGenericDialog rootPane;

    @FXML
    private MFXFontIcon minimizeIcon;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXButton addConfirmButton;

    @FXML
    private MFXTextField skillsField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        addConfirmButton.setOnAction(actionEvent -> onAddConfirm());
    }
    private void onAddConfirm() {

        ArrayList<String> skillsList = new ArrayList<String>(Arrays.asList(skillsField.getText().split(",")));

        for(int i = 0; i < skillsList.size(); i++) {
            Controller.rootPerson.getSkills().add(skillsList.get(i));
        }

        String q = "update Person set skills = ? where id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setString(1,skillsList.toString());
            ps.setInt(2,Controller.rootPerson.getId());
            if(ps.executeUpdate() > 0){

                Controller.createAlert("Skills added successfully.", "");

            }else{

                Controller.createAlert("Error occurred.", "Error");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
