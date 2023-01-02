package com.example.cvdatabase.Controller.EditControllers;

import com.example.cvdatabase.Application;
import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DataManager;
import com.example.cvdatabase.Model.Tag;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class EditTagController implements Initializable {
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
    Controller controller = new Controller();
    int index = 0;
    Tag tag = new Tag();
    @FXML
    private MFXGenericDialog rootPane;
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

        String exTag = Controller.rootPerson.getTags().get(index).getName();

        Controller.rootPerson.getTags().get(index).setName(tagNameField.getText());

        DataManager.getInstance().UpdateTag(tagNameField.getText(), DataManager.getInstance().PullTagByName(exTag).getId());

        FXMLLoader loader;
        loader = new FXMLLoader(Objects.requireNonNull(Application.class.getResource(Config.mainPath)));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller a = loader.getController();
        Scene scene = new Scene(root);
        a.setStage(stage);
        stage.setScene(scene);
        stage.show();
        editConfirmButton.getScene().getWindow().hide();
        a.handleRowSelection();

        //Controller.createAlert("Tag edited successfully", "");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
