package com.example.demo;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.demo.controller.Controller;

public class SettingsMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private final Controller controller;

    public SettingsMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
    }

    public void show() {
        Text settingsTitle = new Text("Settings");
        Font customFontSettings = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 30);
        settingsTitle.setFont(customFontSettings);
        settingsTitle.setStyle("-fx-font-weight: bold; -fx-fill: #FFFFFF;");

        // Create back button with custom font
        Button backButton = createTextButton("Back", customFontSettings);
        backButton.setOnAction(e -> goBackToStartMenu());

        // Create controls button
        Button controlsButton = createTextButton("Controls", customFontSettings);
        controlsButton.setOnAction(e -> goToControlsPage());

        // Toggle button for sound
        ToggleButton soundToggle = createSoundToggle(customFontSettings);
        addToggleButtonHoverEffects(soundToggle); // Add hover effects for the toggle button

        VBox layout = new VBox(20, settingsTitle, soundToggle, controlsButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        Scene settingsScene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(settingsScene);
    }

    private ToggleButton createSoundToggle(Font customFont) {
        ToggleButton soundToggle = new ToggleButton("Sound: ON");
        soundToggle.setFont(customFont);
        soundToggle.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
        soundToggle.setSelected(true);
        soundToggle.setOnAction(e -> {
            if (soundToggle.isSelected()) {
                soundToggle.setText("Sound: ON");
            } else {
                soundToggle.setText("Sound: OFF");
            }
        });
        return soundToggle;
    }

    private Button createTextButton(String text, Font font) {
        Button button = new Button(text);
        button.setFont(font);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
        addHoverEffects(button); // Add hover effects to regular buttons
        return button;
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

    private void addToggleButtonHoverEffects(ToggleButton toggleButton) {
        toggleButton.setOnMouseEntered(e -> {
            toggleButton.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #00FFFF, #FF00FF, #00FFFF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
            toggleButton.setOpacity(0.8);
        });
        toggleButton.setOnMouseExited(e -> {
            toggleButton.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
            toggleButton.setOpacity(1.0);
        });
        toggleButton.setOnMousePressed(e -> {
            toggleButton.setScaleX(0.95);
            toggleButton.setScaleY(0.95);
        });
        toggleButton.setOnMouseReleased(e -> {
            toggleButton.setScaleX(1.0);
            toggleButton.setScaleY(1.0);
        });
    }

    private void goBackToStartMenu() {
        StartMenu startMenu = new StartMenu(stage, screenWidth, screenHeight, controller);
        startMenu.show();
    }

    private void goToControlsPage() {
        ControlsMenu controlsMenu = new ControlsMenu(stage, screenWidth, screenHeight, controller);
        controlsMenu.show();
    }
}
