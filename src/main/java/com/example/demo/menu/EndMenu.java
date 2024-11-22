package com.example.demo.menu;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.demo.controller.Controller;
import com.example.demo.controller.AudioManager;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class EndMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private Controller controller;
    private final AudioManager audioManager;

    private List<Button> menuButtons;
    private int selectedIndex = 0; // Track the currently selected button index

    public EndMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
        this.audioManager = AudioManager.getInstance();
        this.menuButtons = new ArrayList<>();
    }

    public void show() {
        audioManager.pauseBackgroundMusic();
        audioManager.playSoundEffect("/com/example/demo/music/losesound.wav");

        Text title = new Text("Game Over");
        title.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; " +
                "-fx-fill: linear-gradient(from 0% 0% to 0% 100%, #B0C4DE, #DADBDD, #FDDC5C); " +
                "-fx-stroke-width: 1px; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.8), 10, 0.0, 0, 0);");

        Font retroFont = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 50);
        title.setFont(retroFont);

        // Create buttons
        Button menuButton = createStyledButton("Main Menu", retroFont);
        Button restartButton = createStyledButton("Restart", retroFont);
        Button exitButton = createStyledButton("Exit", retroFont);

        // Add buttons to the list for navigation
        menuButtons.add(menuButton);
        menuButtons.add(restartButton);
        menuButtons.add(exitButton);

        // Button actions
        menuButton.setOnAction(e -> controller.goToMainMenu());
        restartButton.setOnAction(e -> restartGame());
        exitButton.setOnAction(e -> stage.close());

        // Layout
        VBox layout = new VBox(20, title, menuButton, restartButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)));

        // Scene setup
        Scene scene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(scene);

        // Disable mouse navigation and enable keyboard navigation
        scene.setOnMouseMoved(e -> scene.setCursor(javafx.scene.Cursor.NONE));
        scene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
    }

    // Handle keyboard input (arrow keys and enter key)
    private void handleKeyPress(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                navigateUp();
                break;
            case DOWN:
                navigateDown();
                break;
            case ENTER:
                selectButton();
                break;
            default:
                break;
        }
    }

    // Navigate up through the buttons
    private void navigateUp() {
        selectedIndex = (selectedIndex > 0) ? selectedIndex - 1 : menuButtons.size() - 1;
        updateButtonStyles();
    }

    // Navigate down through the buttons
    private void navigateDown() {
        selectedIndex = (selectedIndex < menuButtons.size() - 1) ? selectedIndex + 1 : 0;
        updateButtonStyles();
    }

    // Select the currently highlighted button
    private void selectButton() {
        menuButtons.get(selectedIndex).fire();
    }

    // Update button styles based on selection
    private void updateButtonStyles() {
        for (int i = 0; i < menuButtons.size(); i++) {
            Button button = menuButtons.get(i);
            if (i == selectedIndex) {
                button.setStyle("-fx-background-color: transparent; " +
                        "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FFD700, #FF8C00, #FF4500); " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(255,140,0,0.8), 5, 0.0, 2, 2);");
            } else {
                button.setStyle("-fx-background-color: transparent; " +
                        "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
            }
        }
    }

    // Create a styled button with hover effects and click action
    private Button createStyledButton(String text, Font font) {
        Button button = new Button(text);
        button.setFont(font);
        button.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
        button.setFocusTraversable(false);
        addHoverEffects(button);
        return button;
    }

    // Add hover effects to the buttons
    private void addHoverEffects(Button button) {
        button.setOnMouseEntered(e -> {
            button.setText("> " + button.getText());
            button.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #00FFFF, #FF00FF, #00FFFF); " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
            button.setOpacity(0.8);
        });
        button.setOnMouseExited(e -> {
            button.setText(button.getText().substring(2));
            button.setStyle("-fx-background-color: transparent; " +
                    "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); " +
                    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
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

    // Restart the game
    private void restartGame() {
        controller.goToLevel(Controller.LEVEL_ONE_CLASS_NAME);
        audioManager.resumeBackgroundMusic();
    }

    // Go to the main menu
    public void goToMainMenu() {
        controller = new Controller(stage, screenWidth, screenHeight);
        StartMenu startMenu = new StartMenu(stage, screenWidth, screenHeight, controller);
        startMenu.show();
        stage.show();
    }
}
