module com.example.cvdatabase {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires MaterialFX;

    opens com.example.cvdatabase to javafx.fxml;
    exports com.example.cvdatabase;
    exports com.example.cvdatabase.Controller;
    opens com.example.cvdatabase.Controller to javafx.fxml;
    exports com.example.cvdatabase.Model;
    opens com.example.cvdatabase.Model to javafx.fxml;
    exports com.example.cvdatabase.Helpers;
    opens com.example.cvdatabase.Helpers to javafx.fxml;
    exports com.example.cvdatabase.Controller.AddControllers;
    opens com.example.cvdatabase.Controller.AddControllers to javafx.fxml;
    exports com.example.cvdatabase.Controller.EditControllers;
    opens com.example.cvdatabase.Controller.EditControllers to javafx.fxml;

}