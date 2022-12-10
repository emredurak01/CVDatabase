module com.example.cvdatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires MaterialFX;

    opens com.example.cvdatabase to javafx.fxml;
    exports com.example.cvdatabase;
}