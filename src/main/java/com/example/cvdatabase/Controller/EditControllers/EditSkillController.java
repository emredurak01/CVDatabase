package com.example.cvdatabase.Controller.EditControllers;

import com.example.cvdatabase.Application;
import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import com.example.cvdatabase.Model.Person;
import io.github.palexdev.materialfx.controls.MFXButton;
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

public class EditSkillController implements Initializable {

    @FXML
    private MFXGenericDialog rootPane;

    @FXML
    private MFXFontIcon minimizeIcon;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXButton editConfirmButton;

    @FXML
    private MFXTextField skillsField;
    private Controller controller;

    private Stage stage;
    int index;

    public void setStage(Stage stage){

        this.stage = stage;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        for (int i = 0; i < Controller.rootPerson.getSkills().size(); i++) {
            if (Controller.rootPerson.getSkills().get(i).equals(Controller.rootPersonEdit)) {
                skillsField.setText(Controller.rootPerson.getSkills().get(i));
                index = i;
            }
        }

        editConfirmButton.setOnAction(actionEvent -> onEditConfirm(index));


    }

    private void onEditConfirm(int index) {

        String q = "update person set skills=? where id=?";

        try {
            Person person = Controller.rootPerson;
            person.getSkills().set(index, skillsField.getText());

            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setString(1, person.getSkills().get(index));
            ps.setInt(2, person.getId());

            if (ps.executeUpdate() > 0) {

                Controller.createAlert("Skills edited successfully.", "");
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
                a.handleRowSelection();
            } else {

                Controller.createAlert("Skills could not be edited.", "");
            }

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }


    }


}
