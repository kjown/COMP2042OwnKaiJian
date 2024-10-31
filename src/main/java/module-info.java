module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.demo to javafx.fxml; // Open the main package
    exports com.example.demo.controller;    // Export the controller package
    opens com.example.demo.actors to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.view to javafx.fxml;
}
