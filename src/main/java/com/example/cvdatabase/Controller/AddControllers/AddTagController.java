package com.example.cvdatabase.Controller.AddControllers;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.DataManager;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import com.example.cvdatabase.Model.Tag;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddTagController implements Initializable {

    @FXML
    private MFXGenericDialog rootPane;

    public MFXTextField tagNameField;
    public MFXButton addConfirmButton;
    public HBox windowHeader;
    public MFXFontIcon minimizeIcon;
    public MFXFontIcon closeIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        addConfirmButton.setOnAction(actionEvent -> onAddConfirm());
    }

    private void onAddConfirm() {

        Tag tag = new Tag(tagNameField.getText());
        Controller.rootPerson.getTags().add(tag);

        DataManager.getInstance().AddTag(Controller.rootPerson.getId(),tag);


    }
}
