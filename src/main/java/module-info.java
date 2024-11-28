module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

//    requires org.junit.jupiter.api; // JUnit 5
//    requires org.testfx.core; // TestFX for UI testing

    opens com.example.demo.controller to org.junit.platform.commons; // Open to JUnit 5
    opens com.example.demo.menu to javafx.fxml;

    exports com.example.demo.controller;
    opens com.example.demo.actors to javafx.fxml;
    opens com.example.demo.levels to javafx.fxml;
    opens com.example.demo.view to javafx.fxml;
    opens com.example.demo.actors.projectiles to javafx.fxml;
    opens com.example.demo.actors.enemies to javafx.fxml;
}
