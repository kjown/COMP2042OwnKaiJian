module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

//    opens com.example.demo to javafx.fxml; // Open the main package
    exports com.example.demo.controller;    // Export the controller package
    opens com.example.demo.actors to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.view to javafx.fxml;
    opens com.example.demo.menu to javafx.fxml;
    opens com.example.demo.actors.projectiles to javafx.fxml;
    opens com.example.demo.actors.enemies to javafx.fxml;
//    opens com.example.demo.menu to javafx.fxml;
}
