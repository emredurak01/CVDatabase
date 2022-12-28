package com.example.cvdatabase;

import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.IntegerFilter;
import io.github.palexdev.materialfx.filter.StringFilter;
import io.github.palexdev.materialfx.filter.base.AbstractFilter;
import io.github.palexdev.materialfx.font.MFXFontIcon;
import io.github.palexdev.materialfx.skins.MFXTableColumnSkin;
import io.github.palexdev.materialfx.utils.ColorUtils;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.text.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Function;

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


    public Controller(Stage stage) {
        this.stage = stage;
    }


    public void createPerson(String name, String surname, String dateOfBirth, String email, int phone, ArrayList<Education> education) {

        Person person = new Person(name, surname, dateOfBirth, email, phone, education);
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
        exportButton.setOnAction(actionEvent -> onExport());
        removeButton.setOnAction(actionEvent -> onRemove());
        editButton.setOnMouseClicked(actionEvent -> handleRowSelection());


        Education education1 = new Education("IEU","01.01.2020", "01.01.2024");
        Education education2 = new Education("UAL", "01.01.2015", "01.01.2019");
        ArrayList<Education> educationArrayList = new ArrayList<>();
        educationArrayList.add(education1);
        educationArrayList.add(education2);

        //Temporary
        createPerson("Emre", "Durak", "01.01.2001", "emre@ieu.com", 505, educationArrayList);
        createPerson("Can", "Ispartalıoğlu", "01.01.2001", "can@ieu.com", 507, educationArrayList);
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

        root.getItems().addAll(List.of(educations));


        return root;
    }

    private void createTable() {

        MFXTableColumn<Person> idColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(Person::getId));
        MFXTableColumn<Person> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Person::getName));
        MFXTableColumn<Person> surnameColumn = new MFXTableColumn<>("Surname", true, Comparator.comparing(Person::getSurname));
        MFXTableColumn<Person> dateOfBirthColumn = new MFXTableColumn<>("Date of birth", true, Comparator.comparing(Person::getDateOfBirth));
        MFXTableColumn<Person> emailColumn = new MFXTableColumn<>("Email", true, Comparator.comparing(Person::getEmail));
        MFXTableColumn<Person> phoneColumn = new MFXTableColumn<>("Phone", true, Comparator.comparing(Person::getPhone));

        idColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getId));
        nameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getName));
        surnameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getSurname));
        dateOfBirthColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getDateOfBirth));
        emailColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getEmail));
        phoneColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getPhone));

        table.getTableColumns().addAll(idColumn, nameColumn, surnameColumn, dateOfBirthColumn, emailColumn, phoneColumn);
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
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("addDialog.fxml")));
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
    private void onExport() {
        Export.buildCV();
    }

    private void onHelp() {
        Parent root;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("helpDialog.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}