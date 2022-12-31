package com.example.cvdatabase.Controller.AddControllers;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Model.Experience;
import com.example.cvdatabase.Model.Publication;
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

public class AddPublicationController implements Initializable {

    @FXML
    private MFXGenericDialog rootPane;

    @FXML
    private MFXFontIcon minimizeIcon;
    @FXML
    private MFXFontIcon closeIcon;
    @FXML
    private MFXTextField titleField;
    @FXML
    private MFXTextField publisherField;
    @FXML
    private MFXDatePicker publicationDateField;
    @FXML
    private MFXButton addConfirmButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
        addConfirmButton.setOnAction(actionEvent -> onAddConfirm());
    }

    private void onAddConfirm() {
        Publication newPublication = new Publication(titleField.getText(), publisherField.getText(), publicationDateField.getText());
        Controller.rootPerson.getPublications().add(newPublication);
        Controller.createAlert("Publication created successfully.", "");
    }
}
