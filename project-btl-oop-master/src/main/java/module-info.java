module com.example.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.pj to javafx.fxml;
    exports com.example.pj;
    exports com.example.pj.Controller;
    opens com.example.pj.Controller to javafx.fxml;
    exports com.example.pj.Models;
    opens com.example.pj.Models to javafx.fxml;

}