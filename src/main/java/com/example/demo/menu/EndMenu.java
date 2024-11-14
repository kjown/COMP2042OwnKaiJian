package com.example.demo.menu;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.demo.controller.Controller;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class EndMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private Controller controller;

    // Track current button index for keyboard navigation
    private int currentButtonIndex = 0;
    private List<Button> buttons = new ArrayList<>();
    private List<ScaleTransition> pulses = new ArrayList<>();
    private Font retroFont;

    public EndMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
        this.retroFont = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 50);
    }

    public void show() {
        Text title = new Text("Game Over");
        title.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; -fx-fill: linear-gradient(from 0% 0% to 0% 100%, #B0C4DE, #DADBDD, #FDDC5C); -fx-stroke-width: 1px; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.8), 10, 0.0, 0, 0);");
        title.setFont(retroFont);

        Button menuButton = createStyledButton("Main Menu");
        Button restartButton = createStyledButton("Restart");
        Button exitButton = createStyledButton("Exit");

        buttons.add(menuButton);
        buttons.add(restartButton);
        buttons.add(exitButton);

        menuButton.setOnAction(e -> controller.goToMainMenu());
        restartButton.setOnAction(e -> restartGame());
        exitButton.setOnAction(e -> stage.close());

        VBox layout = new VBox(20, title, menuButton, restartButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)));

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        setupKeyboardControls(scene);
        stage.setScene(scene);

        // Highlight the first button by default
        updateButtonStyles();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(retroFont);
        applyDefaultButtonStyle(button);
        button.setFocusTraversable(false);
        return button;
    }

    private void setupKeyboardControls(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.UP) {
                navigateUp();
            } else if (event.getCode() == KeyCode.DOWN) {
                navigateDown();
            } else if (event.getCode() == KeyCode.ENTER) {
                buttons.get(currentButtonIndex).fire();
            }
        });
    }

    private void navigateUp() {
        currentButtonIndex = (currentButtonIndex - 1 + buttons.size()) % buttons.size(); // Navigate up and loop
        updateButtonStyles();
    }

    private void navigateDown() {
        currentButtonIndex = (currentButtonIndex + 1) % buttons.size(); // Navigate down and loop
        updateButtonStyles();
    }

    private void updateButtonStyles() {
        // Stop and remove pulsing effect from all buttons
        for (int i = 0; i < buttons.size(); i++) {
            Button button = buttons.get(i);
            if (i == currentButtonIndex) {
                // Highlighted button style (selected button)
                applyHighlightedButtonStyle(button);
                applyPulsingEffect(button); // Add pulsing effect
            } else {
                // Regular button style
                applyDefaultButtonStyle(button);
                removePulsingEffect(button); // Remove pulsing effect if not selected
            }
        }
    }

    private void applyDefaultButtonStyle(Button button) {
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
    }

    private void applyHighlightedButtonStyle(Button button) {
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #00FFFF, #FF00FF, #00FFFF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 5, 0.0, 3, 3);");
    }

    private void applyPulsingEffect(Button button) {
        // Apply pulse only if it's not already pulsing for the button
        int index = buttons.indexOf(button);
        if (pulses.size() <= index || pulses.get(index) == null) {
            ScaleTransition pulse = new ScaleTransition(Duration.seconds(0.6), button);
            pulse.setFromX(1.0);
            pulse.setFromY(1.0);
            pulse.setToX(1.1);
            pulse.setToY(1.1);
            pulse.setCycleCount(ScaleTransition.INDEFINITE);
            pulse.setAutoReverse(true);
            pulse.play();
            if (pulses.size() <= index) {
                pulses.add(pulse); // Add a new entry to store pulse for each button
            } else {
                pulses.set(index, pulse); // Update the pulse for the button
            }
        }
    }

    private void removePulsingEffect(Button button) {
        int index = buttons.indexOf(button);
        if (index < pulses.size() && pulses.get(index) != null) {
            ScaleTransition pulse = pulses.get(index);
            pulse.stop();
            button.setScaleX(1.0);
            button.setScaleY(1.0);
            pulses.set(index, null); // Reset pulse entry for this button
        }
    }

    public void goToMainMenu() {
        controller = new Controller(stage, screenWidth, screenHeight);
        StartMenu startMenu = new StartMenu(stage, screenWidth, screenHeight, controller);
        startMenu.show();
        stage.show();
    }

    private void restartGame() {
        controller.goToLevel(Controller.LEVEL_ONE_CLASS_NAME);
    }
}
