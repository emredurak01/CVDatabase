package com.example.cvdatabase.Controller.AddControllers;

import com.example.cvdatabase.Application;
import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DataManager;
import com.example.cvdatabase.Helpers.DatabaseConnector;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddInterestController implements Initializable {

    @FXML
    private MFXGenericDialog rootPane;

    @FXML
    private MFXFontIcon minimizeIcon;

    @FXML
    private MFXFontIcon closeIcon;

    @FXML
    private MFXButton addConfirmButton;

    @FXML
    private MFXTextField interestsField;
    private Controller controller;

    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Node) (event.getSource())).getScene().getWindow().hide());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));

        addConfirmButton.setOnAction(actionEvent -> onAddConfirm());
    }

    private void onAddConfirm() {

        ArrayList<String> interestsList = new ArrayList<String>(Arrays.asList(interestsField.getText().split(",")));

        for(int i = 0; i < interestsList.size(); i++) {
            Controller.rootPerson.getInterests().add(interestsList.get(i));
        }


        String q = "update Person set interests = ? where id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);


            String s = DataManager.getInstance().PullInterests(Controller.rootPerson.getId());

            String interestString = interestsList.toString();
            interestString = interestString.replace('[',' ');
            interestString = interestString.replace(']',' ');
            interestString = interestString.trim();


            ps.setString(1, s + ',' + interestString);
            ps.setInt(2,Controller.rootPerson.getId());

            if(ps.executeUpdate() > 0){


                FXMLLoader loader;
                loader = new FXMLLoader(Objects.requireNonNull(Application.class.getResource(Config.mainPath)));
                Parent root = loader.load();
                Controller a = loader.getController();
                Scene scene = new Scene(root);
                a.setStage(stage);
                stage.setScene(scene);
                stage.show();
                addConfirmButton.getScene().getWindow().hide();
                a.handleRowSelection();

                Controller.createAlert("Interests added successfully.", "");

            }else{

                Controller.createAlert("Error occurred.", "Error");

            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }


    }

    public void setStage(Stage stage){

        this.stage = stage;

    }

}
