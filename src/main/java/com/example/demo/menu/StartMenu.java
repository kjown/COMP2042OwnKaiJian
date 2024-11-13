package com.example.demo.menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.demo.controller.Controller;

public class StartMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private final Controller controller;

    private int highScore = 0; // Retrieve actual high score in your logic

    public StartMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
    }

    public void show() {
        Text title = new Text("Sky Battle");
        title.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; -fx-fill: linear-gradient(from 0% 0% to 0% 100%, #B0C4DE, #DADBDD, #FDDC5C); -fx-stroke-width: 1px; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.8), 10, 0.0, 0, 0);");

        Font retroFont = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 50);
        title.setFont(retroFont);

        Text highScoreText = new Text("High Score: " + highScore);
        highScoreText.setFont(retroFont);
        highScoreText.setStyle("-fx-font-size:20px; -fx-fill: white;");

        Button startButton = createStyledButton("Start", retroFont);
        Button settingsButton = createStyledButton("Settings", retroFont);
        Button exitButton = createStyledButton("Exit", retroFont);

        startButton.setOnAction(e -> startGame());
        settingsButton.setOnAction(e -> showSettings());
        exitButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(20, highScoreText, title, startButton, settingsButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)));

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(scene);
    }

    private Button createStyledButton(String text, Font font) {
        Button button = new Button(text);
        button.setFont(font);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
        button.setFocusTraversable(false);
        addHoverEffects(button);
        return button;
    }

    private void startGame() {
        try {
            controller.launchGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSettings() {
        System.out.println("Showing settings...");
        SettingsMenu settingsMenu = new SettingsMenu(stage, screenWidth, screenHeight, controller);
        settingsMenu.show();
    }

    private void addHoverEffects(Button button) {
        button.setOnMouseEntered(e -> {
            button.setText("> " + button.getText());
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #00FFFF, #FF00FF, #00FFFF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
            button.setOpacity(0.8);
        });
        button.setOnMouseExited(e -> {
            button.setText(button.getText().substring(2));
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
            button.setOpacity(1.0);
        });
        button.setOnMousePressed(e -> {
            button.setScaleX(0.95);
            button.setScaleY(0.95);
        });
        button.setOnMouseReleased(e -> {
            button.setScaleX(1.0);
            button.setScaleY(1.0);
        });
    }
}
