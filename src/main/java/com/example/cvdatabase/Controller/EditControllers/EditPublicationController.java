package com.example.cvdatabase.Controller.EditControllers;

import com.example.cvdatabase.Application;
import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import com.example.cvdatabase.Model.Person;
import com.example.cvdatabase.Model.Publication;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
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
    private Stage stage;

    public void setStage(Stage stage) {

        this.stage = stage;

    }

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

        titleField.setText(publication.getTitle());
        publisherField.setText(publication.getPublisher());
        publicationDateField.setText(publication.getPublicationDate());

        editConfirmButton.setOnAction(actionEvent -> onEditConfirm(index));
    }

    private void onEditConfirm(int index) {

        String q = "update publication set title=?,publisher=?,publication_date=? where person_id = ? and id=?";

        try {
            Person person = Controller.rootPerson;

            person.getPublications().get(index).setTitle(titleField.getText());
            person.getPublications().get(index).setPublisher(publisherField.getText());
            person.getExperiences().get(index).setEndDate(publicationDateField.getText());

            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setString(1, person.getPublications().get(index).getTitle());
            ps.setString(2, person.getPublications().get(index).getPublisher());
            ps.setString(3, person.getPublications().get(index).getPublicationDate());
            ps.setInt(4, person.getId());
            ps.setInt(5, person.getPublications().get(index).getId());

            if (ps.executeUpdate() > 0) {

                Controller.createAlert("Publication edited successfully.", "");
                FXMLLoader loader;
                loader = new FXMLLoader(Objects.requireNonNull(Application.class.getResource(Config.mainPath)));
                Parent root = loader.load();
                Controller a = loader.getController();
                Scene scene = new Scene(root);
                a.setStage(stage);
                stage.setScene(scene);
                stage.show();
                editConfirmButton.getScene().getWindow().hide();
                a.handleRowSelection();

            } else {

                Controller.createAlert("Publication could not be edited.", "");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


}

