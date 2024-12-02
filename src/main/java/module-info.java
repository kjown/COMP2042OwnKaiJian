module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    exports com.example.demo.controller;
    opens com.example.demo.actors to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.view to javafx.fxml;
    opens com.example.demo.menu to javafx.fxml;
}