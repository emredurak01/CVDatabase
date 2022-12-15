package com.example.cvdatabase;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private final Stage stage;
    Database database = new Database();
    ObservableList<Person> personList = FXCollections.observableArrayList();
    @FXML
    private MFXButton addButton;
    @FXML
    private MFXButton removeButton;
    @FXML
    private MFXButton editButton;
    @FXML
    private MFXButton exportButton;
    @FXML
    private MFXButton helpButton;
    @FXML
    private MFXTableView<Person> table;
    @FXML
    private MFXFontIcon closeIcon;
    @FXML
    private MFXFontIcon minimizeIcon;
    @FXML
    private MFXFontIcon alwaysOnTopIcon;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private HBox windowHeader;
    private double x;
    private double y;


    public Controller(Stage stage) {
        this.stage = stage;
    }


    public void createPerson(int id, String name, String surname, String dateOfBirth, String email, int phone, ArrayList<String> education, ArrayList<String> experiences,
                             ArrayList<String> publications, ArrayList<String> interests, ArrayList<String> skills, ArrayList<String> tags) {

        Person person = new Person(id, name, surname, dateOfBirth, email, phone, education, experiences, publications, interests, skills, tags);
        personList.add(person);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        closeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> Platform.exit());
        minimizeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> ((Stage) rootPane.getScene().getWindow()).setIconified(true));
        alwaysOnTopIcon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            boolean newVal = !stage.isAlwaysOnTop();
            alwaysOnTopIcon.pseudoClassStateChanged(PseudoClass.getPseudoClass("always-on-top"), newVal);
            stage.setAlwaysOnTop(newVal);
        });

        windowHeader.setOnMousePressed(event -> {
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        });
        windowHeader.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() + x);
            stage.setY(event.getScreenY() + y);
        });

        helpButton.setOnAction(actionEvent -> onHelp());

        //Temporary
        createPerson(0, "Emre", "Durak", "01.01.2001", "emre@ieu.com", 505, null,
                null, null, null, null, null);
        createPerson(1, "Can", "Ispartalıoğlu", "01.01.2001", "can@ieu.com", 507, null,
                null, null, null, null, null);
        createTable();
    }

    private void createTable() {

        MFXTableColumn<Person> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(Person::getId));
        MFXTableColumn<Person> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Person::getName));
        MFXTableColumn<Person> surnameColumn = new MFXTableColumn<>("Surname", true, Comparator.comparing(Person::getSurname));
        MFXTableColumn<Person> dateOfBirthColumn = new MFXTableColumn<>("Date of birth", true, Comparator.comparing(Person::getDateOfBirth));
        MFXTableColumn<Person> emailColumn = new MFXTableColumn<>("Email", true, Comparator.comparing(Person::getEmail));
        MFXTableColumn<Person> phoneColumn = new MFXTableColumn<>("Phone", true, Comparator.comparing(Person::getPhone));
        MFXTableColumn<Person> educationColumn = new MFXTableColumn<>("Education", true);
        MFXTableColumn<Person> experiencesColumn = new MFXTableColumn<>("Experiences", true);
        MFXTableColumn<Person> publicationsColumn = new MFXTableColumn<>("Publications", true);
        MFXTableColumn<Person> interestsColumn = new MFXTableColumn<>("Interests", true);
        MFXTableColumn<Person> skillsColumn = new MFXTableColumn<>("Skills", true);
        MFXTableColumn<Person> tagsColumn = new MFXTableColumn<>("Tags", true);

        idColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getId));
        nameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getName));
        surnameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getSurname));
        dateOfBirthColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getDateOfBirth));
        emailColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getEmail));
        phoneColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getPhone));
        educationColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getEducation));
        experiencesColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getExperiences));
        publicationsColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getPublications));
        interestsColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getInterests));
        skillsColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getSkills));
        tagsColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getTags));

        table.getTableColumns().addAll(idColumn, nameColumn, surnameColumn, dateOfBirthColumn, emailColumn, phoneColumn, educationColumn, experiencesColumn, publicationsColumn,
                interestsColumn, skillsColumn, tagsColumn);

        table.getFilters().addAll(
                new IntegerFilter<>("ID", Person::getId),
                new StringFilter<>("Name", Person::getName),
                new StringFilter<>("Surname", Person::getSurname),
                new StringFilter<>("Date of birth", Person::getDateOfBirth),
                new StringFilter<>("Email", Person::getEmail),
                new IntegerFilter<>("Phone", Person::getPhone)
        );

        table.setItems(personList);

    }

    private void onAdd() {
        database.addPerson("Emre");
    }

    private void onList() {
        database.listPersons();
    }

    private void onRemove() {
        database.removePersons();
    }

    private void onHelp() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("dialog.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setTitle("New");
            primaryStage.setScene(scene);
            primaryStage.initModality(Modality.NONE);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}