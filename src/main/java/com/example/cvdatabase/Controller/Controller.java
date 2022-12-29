package com.example.cvdatabase.Controller;

import com.example.cvdatabase.*;
import com.example.cvdatabase.Helpers.Config;
import com.example.cvdatabase.Helpers.DataManager;
import com.example.cvdatabase.Helpers.Database;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    public Stage stage;
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

    public Controller(){

    }

    public Controller(Stage stage) {
        this.stage = stage;
    }


    public void createPerson(String name, String surname, String dateOfBirth, String email, String phone, ArrayList<Education> education
    ,ArrayList<Experience> experiences, ArrayList<Publication> publications,ArrayList<String> interests,ArrayList<String> skills) {

        Person person = new Person(name, surname, dateOfBirth, email, phone, education, experiences, publications,interests,skills);
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
        editButton.setOnMouseClicked(actionEvent -> handleRowSelection());


        Education education1 = new Education("IEU","01.01.2020", "01.01.2024");
        Education education2 = new Education("UAL", "01.01.2015", "01.01.2019");
        Experience experience1 = new Experience("Microfot", "01.01.02323", "1");
        Publication publication1 = new Publication("AI", "ben", "01.52.023");


        ArrayList<Education> educationArrayList = new ArrayList<>();
        educationArrayList.add(education1);
        educationArrayList.add(education2);

        ArrayList<Experience> experienceArrayList = new ArrayList<>();
        experienceArrayList.add(experience1);

        ArrayList<Publication> publicationArrayList = new ArrayList<>();
        publicationArrayList.add(publication1);

        ArrayList<String> interests = new ArrayList<>();
        interests.add("backgammon");
        interests.add("football");

        ArrayList<String> skills = new ArrayList<>();
        skills.add("asd");
        skills.add("asdfg");




        createPerson("Emre", "Durak", "01.01", "e", "505", educationArrayList, experienceArrayList,publicationArrayList,interests,skills);
        createTable();

    }


    private void handleRowSelection() {
        ObservableMap<Integer, Person> listValues = table.getSelectionModel().getSelection();
        ObservableList<Person> personList = FXCollections.observableArrayList(listValues.values());

        treeView.setRoot(createTreeView(personList));
    }

    private MFXTreeItem<String> createTreeView(ObservableList<Person> personList) {


        String name = personList.listIterator().next().getName();
        String surname = personList.listIterator().next().getSurname();

        MFXTreeItem<String> root = new MFXTreeItem<>(name +" " +surname);

        MFXTreeItem<String> educations = new MFXTreeItem<>("Educations");


        for(int i = 0 ; i < 2; i++) {

            if(personList.listIterator().next().getEducation()!=null){

                String educationName = personList.listIterator().next().getEducation().get(i).getName();
                String educationStartDate = personList.listIterator().next().getEducation().get(i).getStartDate();
                String educationEndDate = personList.listIterator().next().getEducation().get(i).getEndDate();

                MFXTreeItem<String> educationsName = new MFXTreeItem<>(educationName);
                educationsName.getItems().addAll(List.of(
                        new MFXTreeItem<>("Start Date: " +educationStartDate),
                        new MFXTreeItem<>("End Date: " +educationEndDate)
                ));
                educations.getItems().addAll(List.of(educationsName));

            }

        }

        root.getItems().addAll(List.of(educations));


        return root;
    }

    public void createTable() {

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

        //personList = FXCollections.observableArrayList(DataManager.PullPersons());

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

    private void onList() {}

    private void onRemove() {
        table.getItems().removeAll(table.getSelectionModel().getSelectedValues());
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

    public MFXTableView<Person> getTable() {
        return table;
    }
}