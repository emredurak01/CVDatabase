package com.example.cvdatabase.Controller.EditControllers;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.DataManager;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class EditTagController implements Initializable {
    Controller controller = new Controller();
    @FXML
    private MFXGenericDialog rootPane;
    @FXML
    public MFXTextField tagNameField;
    @FXML
    public MFXButton editConfirmButton;
    @FXML
    public HBox windowHeader;
    @FXML
    public MFXFontIcon minimizeIcon;
    @FXML
    public MFXFontIcon closeIcon;
    int index = 0;
    Tag tag = new Tag();

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        for (int i = 0; i < Controller.rootPerson.getTags().size(); i++) {
            if (Controller.rootPerson.getTags().get(i).getName().equals(Controller.rootPersonEdit)) {
                tag = Controller.rootPerson.getTags().get(i);
                index = i;
            }
        }

        tagNameField.setText(tag.getName());

        editConfirmButton.setOnAction(actionEvent -> onEditConfirm());
    }

    private void onEditConfirm() {
        Controller.rootPerson.getTags().get(index).setName(tagNameField.getText());
        Controller.createAlert("Tag edited successfully", "");
    }
}
