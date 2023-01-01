package com.example.cvdatabase.Controller.AddControllers;

import com.example.cvdatabase.Application;
import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DataManager;
import com.example.cvdatabase.Helpers.DatabaseConnector;
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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddTagController implements Initializable {

    @FXML
    private MFXGenericDialog rootPane;
    @FXML
    public MFXTextField tagNameField;
    @FXML
    public MFXButton addConfirmButton;
    @FXML
    public HBox windowHeader;
    @FXML
    public MFXFontIcon minimizeIcon;
    @FXML
    public MFXFontIcon closeIcon;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        addConfirmButton.setOnAction(actionEvent -> onAddConfirm());
    }

    public void setStage(Stage stage) {

        this.stage = stage;

    }

    private void onAddConfirm() {

        ArrayList<String> tagNameList = new ArrayList<>(Arrays.asList(tagNameField.getText().split(",")));

        for(int i = 0; i < tagNameList.size(); i++) {
            Tag tag = new Tag();
            tag.setName(tagNameList.get(i));
            Controller.rootPerson.getTags().add(tag);
            DataManager.getInstance().AddTag(Controller.rootPerson.getId(),tag);
        }

        FXMLLoader loader;
        loader = new FXMLLoader(Objects.requireNonNull(Application.class.getResource(Config.mainPath)));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Controller a = loader.getController();
        assert root != null;
        Scene scene = new Scene(root);
        a.setStage(stage);
        stage.setScene(scene);
        stage.show();
        addConfirmButton.getScene().getWindow().hide();
        a.handleRowSelection();


    }
}
