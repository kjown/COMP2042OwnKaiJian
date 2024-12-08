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

/**
 * The EndMenu class represents the end screen of the game, providing the user with options to
 * go back to the main menu, restart the game, or exit the game.
 */
public class EndMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private Controller controller;

    /**
     * Constructor to initialize the EndMenu.
     *
     * @param stage The primary stage of the application.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     * @param controller The controller that manages the game flow.
     */
    public EndMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
    }

    /**
     * Displays the EndMenu, showing the game over message and the available options.
     */
    public void show() {
        Text title = new Text("Game Over");
        title.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; -fx-fill: linear-gradient(from 0% 0% to 0% 100%, #B0C4DE, #DADBDD, #FDDC5C); -fx-stroke-width: 1px; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.8), 10, 0.0, 0, 0);");

        Font retroFont = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 50);
        title.setFont(retroFont);

        // Create the buttons with actions
        Button menuButton = createStyledButton("Main Menu", retroFont);
        Button restartButton = createStyledButton("Restart", retroFont);
        Button exitButton = createStyledButton("Exit", retroFont);

        menuButton.setOnAction(e -> controller.goToMainMenu());
        restartButton.setOnAction(e -> restartGame());
        exitButton.setOnAction(e -> stage.close());

        // Layout for the EndMenu
        VBox layout = new VBox(20, title, menuButton, restartButton, exitButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)));

        Scene scene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(scene);
    }

    /**
     * Creates a styled button with a given text and font.
     *
     * @param text The text displayed on the button.
     * @param font The font used for the button's text.
     * @return The styled button.
     */
    public Button createStyledButton(String text, Font font) {
        Button button = new Button(text);
        button.setFont(font);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
        button.setFocusTraversable(false);
        addHoverEffects(button);
        return button;
    }

    /**
     * Navigates to the main menu by creating a new Controller and showing the StartMenu.
     */
    public void goToMainMenu() {
        controller = new Controller(stage, screenWidth, screenHeight);

        StartMenu startMenu = new StartMenu(stage, screenWidth, screenHeight, controller);
        startMenu.show();

        stage.show();
    }

    /**
     * Restarts the game by going to the first level and resuming background music.
     */
    private void restartGame() {
        controller.goToLevel(Controller.LEVEL_ONE_CLASS_NAME);
        AudioManager.getInstance().resumeBackgroundMusic();
    }

    /**
     * Adds hover effects to the given button, including text changes and style adjustments.
     *
     * @param button The button to which the hover effects will be added.
     */
    private void addHoverEffects(Button button) {
        button.setOnMouseEntered(e -> {
            button.setText("> " + button.getText());
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #00FFFF, #FF00FF, #00FFFF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
            button.setOpacity(0.8);
            AudioManager.getInstance().playSoundEffect("/com/example/demo/music/menu-button-hover.wav");
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
