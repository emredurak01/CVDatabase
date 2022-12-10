package com.example.cvdatabase;

import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.filter.StringFilter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

public class TableController implements Initializable {

    @FXML
    private MFXTableView<Person> table;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createTable();
    }
    private void createTable() {
        MFXTableColumn<Person> nameColumn = new MFXTableColumn<>("Name", true, Comparator.comparing(Person::getName));

        nameColumn.setRowCellFactory(person -> new MFXTableRowCell<>(Person::getName));
        table.getTableColumns().addAll(nameColumn);
        table.getFilters().addAll(
                new StringFilter<>("Name", Person::getName)
        );


    }
}
