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

    // High score, should be dynamically retrieved or calculated
    private int highScore = 0;

    public StartMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
    }

    // Display the Start Menu
    public void show() {
        VBox layout = createLayout();
        Scene scene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(scene);
    }

    // Create and arrange UI components in the layout
    private VBox createLayout() {
        Text title = createTitleText("Sky Battle");
        Text highScoreText = createHighScoreText();
        Font retroFont = loadRetroFont();

        // Create buttons and assign their actions
        Button startButton = createStyledButton("Start", retroFont, this::startGame);
        Button settingsButton = createStyledButton("Settings", retroFont, this::showSettings);
        Button exitButton = createStyledButton("Exit", retroFont, () -> stage.close());

        // Arrange the components in a vertical box
        VBox layout = new VBox(20, highScoreText, title, startButton, settingsButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)));
        return layout;
    }

    // Create the title text with style
    private Text createTitleText(String text) {
        Text title = new Text(text);
        title.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; " +
                "-fx-fill: linear-gradient(from 0% 0% to 0% 100%, #B0C4DE, #DADBDD, #FDDC5C); " +
                "-fx-stroke-width: 1px; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.8), 10, 0.0, 0, 0);");
        title.setFont(loadRetroFont(50));
        return title;
    }

    // Create the high score text
    private Text createHighScoreText() {
        Text highScoreText = new Text("High Score: " + highScore);
        highScoreText.setFont(loadRetroFont(20));
        highScoreText.setStyle("-fx-fill: white;");
        return highScoreText;
    }

    // Load the custom retro font
    private Font loadRetroFont() {
        return loadRetroFont(50); // Default size if not specified
    }

    // Load the custom retro font with a specific size
    private Font loadRetroFont(int size) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), size);
    }

    // Create a styled button with hover effects and a click action
    private Button createStyledButton(String text, Font font, Runnable action) {
        Button button = new Button(text);
        button.setFont(font);
        button.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
        button.setFocusTraversable(false);
        addHoverEffects(button);
        button.setOnAction(e -> action.run());
        return button;
    }

    // Add hover effects to a button
    private void addHoverEffects(Button button) {
        button.setOnMouseEntered(e -> applyHoverStyle(button, true));
        button.setOnMouseExited(e -> applyHoverStyle(button, false));
        button.setOnMousePressed(e -> scaleButton(button, 0.95));
        button.setOnMouseReleased(e -> scaleButton(button, 1.0));
    }

    // Apply hover style to a button
    private void applyHoverStyle(Button button, boolean isHovered) {
        if (isHovered) {
            button.setText("> " + button.getText());
            button.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #00FFFF, #FF00FF, #00FFFF); " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
            button.setOpacity(0.8);
        } else {
            button.setText(button.getText().substring(2));
            button.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
            button.setOpacity(1.0);
        }
    }

    // Scale a button when pressed/released
    private void scaleButton(Button button, double scale) {
        button.setScaleX(scale);
        button.setScaleY(scale);
    }

    // Action to start the game
    private void startGame() {
        try {
            controller.launchGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Action to show the settings menu
    private void showSettings() {
        System.out.println("Showing settings...");
        SettingsMenu settingsMenu = new SettingsMenu(stage, screenWidth, screenHeight, controller);
        settingsMenu.show();
    }
}
