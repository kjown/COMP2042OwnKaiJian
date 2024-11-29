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
 * The StartMenu class represents the start screen of the game.
 * It provides options for starting the game, accessing settings, and exiting the game.
 * It also displays the high score and a cutscene with a typing effect before starting the game.
 */
public class StartMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private final Controller controller;

    // High score, should be dynamically retrieved or calculated
    private int highScore = 0;

    /**
     * Constructor to initialize the StartMenu.
     *
     * @param stage The stage (window) where the start menu will be displayed.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     * @param controller The controller for managing game logic and settings.
     */
    public StartMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
    }

    /**
     * Displays the start menu scene with the high score, title, and buttons.
     */
    public void show() {
        VBox layout = createLayout();
        Scene scene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(scene);
    }

    /**
     * Creates the layout for the start menu, including the title, high score, and buttons.
     *
     * @return The VBox layout containing the start menu components.
     */
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

    /**
     * Creates the title text with a specified style.
     *
     * @param text The text to be displayed as the title.
     * @return A Text object for the title.
     */
    private Text createTitleText(String text) {
        Text title = new Text(text);
        title.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; " +
                "-fx-fill: linear-gradient(from 0% 0% to 0% 100%, #B0C4DE, #DADBDD, #FDDC5C); " +
                "-fx-stroke-width: 1px; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.8), 10, 0.0, 0, 0);");
        title.setFont(loadRetroFont(50));
        return title;
    }

    /**
     * Creates the high score text, displaying the current high score.
     *
     * @return A Text object for the high score.
     */
    private Text createHighScoreText() {
        Text highScoreText = new Text("High Score: " + highScore);
        highScoreText.setFont(loadRetroFont(20));
        highScoreText.setStyle("-fx-fill: white;");
        return highScoreText;
    }

    /**
     * Loads the custom retro font with the default size of 50.
     *
     * @return The Font object for the retro font.
     */
    private Font loadRetroFont() {
        return loadRetroFont(50); // Default size if not specified
    }

    /**
     * Loads the custom retro font with a specified size.
     *
     * @param size The font size to be used.
     * @return The Font object for the retro font with the specified size.
     */
    private Font loadRetroFont(int size) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), size);
    }

    /**
     * Creates a styled button with hover effects and an action to be executed when clicked.
     *
     * @param text The text displayed on the button.
     * @param font The font to be used for the button text.
     * @param action The action to be performed when the button is clicked.
     * @return The styled Button object.
     */
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

    /**
     * Adds hover effects to a button, including a hover sound effect.
     *
     * @param button The button to which the hover effects will be applied.
     */
    private void addHoverEffects(Button button) {
        button.setOnMouseEntered(e -> {
            applyHoverStyle(button, true);
            // Play hover sound
            AudioManager.getInstance().playSoundEffect("/com/example/demo/music/menu-button-hover.wav");
        });
        button.setOnMouseExited(e -> applyHoverStyle(button, false));
        button.setOnMousePressed(e -> scaleButton(button, 0.95));
        button.setOnMouseReleased(e -> scaleButton(button, 1.0));
    }

    /**
     * Applies the hover style to a button (changes text and style).
     *
     * @param button The button to apply the hover style to.
     * @param isHovered Whether the button is hovered or not.
     */
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

    /**
     * Scales the button when pressed/released to give a visual effect.
     *
     * @param button The button to scale.
     * @param scale The scale factor to apply.
     */
    private void scaleButton(Button button, double scale) {
        button.setScaleX(scale);
        button.setScaleY(scale);
    }

    /**
     * Action to start the game by displaying the cutscene.
     */
    private void startGame() {
        try {
            showCutscene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the settings menu when the settings button is clicked.
     */
    private void showSettings() {
        System.out.println("Showing settings...");
        SettingsMenu settingsMenu = new SettingsMenu(stage, screenWidth, screenHeight, controller);
        settingsMenu.show();
    }

    /**
     * Displays a cutscene with a typing effect and a prompt to start the game after the cutscene finishes.
     */
    private void showCutscene() {
        // Story text for the cutscene
        String storyText = "In the skies above, a battle rages between rival forces. You are the chosen hero...\n" +
                "Your mission is to defeat the enemy and claim victory for your people.\n\n" +
                "Get ready to dive into the action...";

        // Create a text node to display the story
        Text cutsceneText = new Text();
        cutsceneText.setFont(loadRetroFont(30));
        cutsceneText.setFill(javafx.scene.paint.Color.web("#39FF14")); // Neon Green
        cutsceneText.setFill(javafx.scene.paint.Color.WHITE);
        cutsceneText.setWrappingWidth(screenWidth * 0.8);  // Wrap the text for readability
        cutsceneText.setStyle("-fx-effect: dropshadow(gaussian, rgba(57, 255, 20, 1), 10, 0.0, 0, 0);");  // Neon Green Glow

        // Create a layout for the cutscene text
        VBox cutsceneLayout = new VBox(20, cutsceneText);
        cutsceneLayout.setAlignment(Pos.CENTER);
        cutsceneLayout.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Color.BLACK, null, null)));

        // Scene for the cutscene
        Scene cutsceneScene = new Scene(cutsceneLayout, screenWidth, screenHeight);
        stage.setScene(cutsceneScene);

        AudioManager.getInstance().playSoundEffect("/com/example/demo/music/typing_sound.mp3");

        // Start typing effect
        typeWriterEffect(cutsceneText, storyText, () -> {
            // Prompt the user to press SPACE to proceed
            Text promptText = new Text("Press SPACE to start the battle...");
            promptText.setFont(loadRetroFont(25));
            promptText.setFill(javafx.scene.paint.Color.YELLOW);
            cutsceneLayout.getChildren().add(promptText);

            // Listen for SPACE key press to start the game
            cutsceneScene.setOnKeyPressed(event -> {
                if (event.getCode() == javafx.scene.input.KeyCode.SPACE) {
                    controller.launchGame();  // Proceed to start the game
                }
            });
        });
    }

    /**
     * Creates a typewriter effect for the cutscene text.
     *
     * @param textNode The Text node to display the effect.
     * @param fullText The full text to be typed out.
     * @param onFinish The action to perform after the typing effect is complete.
     */
    private void typeWriterEffect(Text textNode, String fullText, Runnable onFinish) {
        StringBuilder currentText = new StringBuilder();
        new Thread(() -> {
            for (char c : fullText.toCharArray()) {
                try {
                    currentText.append(c);
                    // Update the text on the JavaFX Application thread
                    javafx.application.Platform.runLater(() -> textNode.setText(currentText.toString()));

                    Thread.sleep(30);  // Adjust typing speed
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // After typing is complete, run the callback to show the next prompt
            javafx.application.Platform.runLater(onFinish);
        }).start();
    }
}
