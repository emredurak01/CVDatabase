package com.example.cvdatabase.Controller;

import com.example.cvdatabase.*;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DataManager;
import com.example.cvdatabase.Helpers.Database;
import com.example.cvdatabase.Helpers.DatabaseConnector;
import com.example.cvdatabase.Model.Education;
import com.example.cvdatabase.Model.Experience;
import com.example.cvdatabase.Model.Person;
import com.example.cvdatabase.Model.Publication;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class Controller implements Initializable {
    public Stage stage;
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
    private MFXButton displayButton;

    @FXML
    private MFXButton attributeButton;
    @FXML
    public MFXTableView<Person> table;
    @FXML
    private MFXTreeView<String> treeView;
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

    public Controller() {

    }

    public Controller(Stage stage) {
        this.stage = stage;
    }


    public void createPerson(String name, String surname, String dateOfBirth, String email, String phone, String interests
            , String skills) {

        ArrayList<String> interestsList = new ArrayList<String>(Arrays.asList(interests.split(",")));
        ArrayList<String> skillsList = new ArrayList<String>(Arrays.asList(skills.split(",")));

        Person person = new Person(name, surname, dateOfBirth, email, phone, interestsList, skillsList);
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

        addButton.setOnAction(actionEvent -> onAdd());
        editButton.setOnAction(actionEvent -> onEdit());
        helpButton.setOnAction(actionEvent -> onHelp());
        exportButton.setOnAction(actionEvent -> onExport(table));
        removeButton.setOnAction(actionEvent -> onRemove());
        displayButton.setOnAction(actionEvent -> handleRowSelection());


        createTable();

    }


    private void handleRowSelection() {
        ObservableMap<Integer, Person> listValues = table.getSelectionModel().getSelection();
        ObservableList<Person> personList = FXCollections.observableArrayList(listValues.values());

        if(table.getSelectionModel().getSelectedValues().size() > 0){

            treeView.setRoot(createTreeView(personList));

        }
    }

    private MFXTreeItem<String> createTreeView(ObservableList<Person> personList) {


        String name = personList.listIterator().next().getName();
        String surname = personList.listIterator().next().getSurname();

        MFXTreeItem<String> root = new MFXTreeItem<>(name + " " + surname);

        MFXTreeItem<String> educations = new MFXTreeItem<>("Educations");

        if(personList.listIterator().next().getEducation() != null){
            for (int i = 0; i < personList.listIterator().next().getEducation().size(); i++) {

                String educationName = personList.listIterator().next().getEducation().get(i).getName();
                String educationStartDate = personList.listIterator().next().getEducation().get(i).getStartDate();
                String educationEndDate = personList.listIterator().next().getEducation().get(i).getEndDate();

                MFXTreeItem<String> educationsName = new MFXTreeItem<>(educationName);
                educationsName.getItems().addAll(List.of(
                        new MFXTreeItem<>("Start Date: " + educationStartDate),
                        new MFXTreeItem<>("End Date: " + educationEndDate)
                ));
                educations.getItems().addAll(List.of(educationsName));

            }
        }

        MFXTreeItem<String> experiences = new MFXTreeItem<>("Experiences");

        if (personList.listIterator().next().getExperiences() != null) {
            for (int i = 0; i < personList.listIterator().next().getExperiences().size(); i++) {



                String title = personList.listIterator().next().getExperiences().get(i).getTitle();
                String experienceStartDate = personList.listIterator().next().getExperiences().get(i).getStartDate();
                String experienceEndDate = personList.listIterator().next().getPublications().get(i).getPublicationDate();

                MFXTreeItem<String> experienceTitle = new MFXTreeItem<>(title);
                experienceTitle.getItems().addAll(List.of(
                        new MFXTreeItem<>("Start Date: " + experienceStartDate),
                        new MFXTreeItem<>("End Date: " + experienceEndDate)
                ));
                experiences.getItems().addAll(List.of(experienceTitle));



            }
        }

        MFXTreeItem<String> publications = new MFXTreeItem<>("Publications");
        if (personList.listIterator().next().getPublications() != null) {
            for (int i = 0; i < personList.listIterator().next().getPublications().size(); i++) {


                String title = personList.listIterator().next().getPublications().get(i).getTitle();
                String publisher = personList.listIterator().next().getPublications().get(i).getPublisher();
                String publicationDate = personList.listIterator().next().getPublications().get(i).getPublicationDate();

                MFXTreeItem<String> publicationTitle = new MFXTreeItem<>(title);
                publicationTitle.getItems().addAll(List.of(
                        new MFXTreeItem<>("Publisher: " + publisher),
                        new MFXTreeItem<>("Publication date: " + publicationDate)
                ));
                publications.getItems().addAll(List.of(publicationTitle));



            }
        }

        MFXTreeItem<String> interests = new MFXTreeItem<>("Interests");

        for (int i = 0; i < personList.listIterator().next().getInterests().size(); i++) {
            if (personList.listIterator().next().getInterests() != null) {

                String interestName = personList.listIterator().next().getInterests().get(i);
                MFXTreeItem<String> interestsItem = new MFXTreeItem<>(interestName);
                interests.getItems().addAll(List.of(interestsItem));
            }
        }

        MFXTreeItem<String> skills = new MFXTreeItem<>("Skills");

        for (int i = 0; i < personList.listIterator().next().getSkills().size(); i++) {
            if (personList.listIterator().next().getSkills() != null) {

                String skillName = personList.listIterator().next().getSkills().get(i);
                MFXTreeItem<String> skillsItem = new MFXTreeItem<>(skillName);
                skills.getItems().addAll(List.of(skillsItem));
            }
        }

        root.getItems().addAll(List.of(educations, experiences, publications, interests, skills));


        return root;
    }

    public void createTable() {

        table.getSelectionModel().setAllowsMultipleSelection(false);

        MFXTableColumn<Person> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(Person::getId));
        MFXTableColumn<Person> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Person::getName));
        MFXTableColumn<Person> surnameColumn = new MFXTableColumn<>("Surname", true, Comparator.comparing(Person::getSurname));
        MFXTableColumn<Person> dateOfBirthColumn = new MFXTableColumn<>("Date of birth", true, Comparator.comparing(Person::getBirthdate));
        MFXTableColumn<Person> emailColumn = new MFXTableColumn<>("Email", true, Comparator.comparing(Person::getEmail));
        MFXTableColumn<Person> phoneColumn = new MFXTableColumn<>("Phone", true, Comparator.comparing(Person::getPhone));

        idColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getId));
        nameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getName));
        surnameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getSurname));
        dateOfBirthColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getBirthdate));
        emailColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getEmail));
        phoneColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getPhone));

        table.getTableColumns().addAll(idColumn, nameColumn, surnameColumn, dateOfBirthColumn, emailColumn, phoneColumn);
        table.getFilters().addAll(
                new IntegerFilter<>("ID", Person::getId),
                new StringFilter<>("Name", Person::getName),
                new StringFilter<>("Surname", Person::getSurname),
                new StringFilter<>("Date of birth", Person::getBirthdate),
                new StringFilter<>("Email", Person::getEmail),
                new StringFilter<>("Phone", Person::getPhone)
        );

        personList = FXCollections.observableArrayList(DataManager.PullPersons());

        table.setItems(personList);
        table.update();
    }

    private void onAdd() {
        Parent root;
        FXMLLoader loader;
        try {

            loader = new FXMLLoader(Objects.requireNonNull(Application.class.getResource(Config.addDialogPath)));
            root = loader.load();

            DialogController a = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onEdit() {
    }

    private void onRemove() {


        Person p = table.getSelectionModel().getSelectedValues().iterator().next();

        String q = "delete from Person where id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1,p.getId());

            if(ps.executeUpdate()>0){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Selected CV is deleted, successfully!");
                alert.setTitle("Confirmation");
                alert.showAndWait();
                table.getItems().remove(p);

            }else{

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Selected CV is not deleted, something went wrong!");
                alert.setTitle("Error");
                alert.showAndWait();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void onExport(MFXTableView<Person> table) {
        Export.buildCV(table);
    }

    private void onHelp() {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource(Config.helpDialogPath)));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}