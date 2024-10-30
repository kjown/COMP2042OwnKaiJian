package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.demo.controller.Controller;

public class StartMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private final Controller controller;

    public StartMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
    }

    public void show() {
        Text title = new Text("Sky Battle");
        title.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-fill: green;");

        Button startButton = new Button("Start");
//        Button highScoreButton = new Button("High Score");
        Button settingsButton = new Button("Settings");
        Button exitButton = new Button("Exit");

        // Add actions for each button
        startButton.setOnAction(e -> startGame());
//        highScoreButton.setOnAction(e -> showHighScore());
        settingsButton.setOnAction(e -> showSettings());
        exitButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(20, title, startButton, settingsButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(scene);
    }

    private void startGame() {
        try {
            controller.launchGame();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void showHighScore() {
//        // Code to show high score
//        System.out.println("Showing high score...");
//    }

    private void showSettings() {
        // Code to show settings
        System.out.println("Showing settings...");
    }
}
