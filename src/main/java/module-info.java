module com.example.cvdatabase {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cvdatabase to javafx.fxml;
    exports com.example.cvdatabase;
}