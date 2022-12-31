package com.example.cvdatabase.Controller.EditControllers;

import com.example.cvdatabase.Controller.Controller;
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

public class EditPublicationController implements Initializable {


    Publication publication = new Publication();
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
    private MFXTextField publisherField;
    @FXML
    private MFXDatePicker publicationDateField;
    @FXML
    private MFXButton editConfirmButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        for (int i = 0; i < Controller.rootPerson.getPublications().size(); i++) {
            if (Controller.rootPerson.getPublications().get(i).getTitle().equals(Controller.rootPersonEdit)) {
                publication = Controller.rootPerson.getPublications().get(i);
                index = i;
            }
        }
        System.out.println(index);

        titleField.setText(publication.getTitle());
        publisherField.setText(publication.getPublisher());
        publicationDateField.setText(publication.getPublicationDate());

        editConfirmButton.setOnAction(actionEvent -> onEditConfirm(index));
    }

    private void onEditConfirm(int index) {
        Controller.rootPerson.getPublications().get(index).setTitle(titleField.getText());
        Controller.rootPerson.getPublications().get(index).setPublisher(publisherField.getText());
        Controller.rootPerson.getPublications().get(index).setPublicationDate(publicationDateField.getText());
        Controller.createAlert("Publication edited successfully", "");
    }


}

