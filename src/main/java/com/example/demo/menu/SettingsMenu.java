package com.example.demo.menu;

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
import javafx.scene.media.AudioClip; // Import AudioClip for sound effects
import com.example.demo.controller.Controller;

/**
 * The SettingsMenu class represents the settings menu in the game.
 * It provides options to adjust the sound settings and navigate to the controls page or back to the main menu.
 */
public class SettingsMenu {

    // Stage for the current scene, width, and height of the screen
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;

    // Controller for managing game logic
    private final Controller controller;

    // AudioClip for the hover sound effect
    private final AudioClip hoverSound;

    /**
     * Constructor that initializes the SettingsMenu.
     *
     * @param stage The stage (window) in which the settings menu will be displayed.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     * @param controller The controller that manages the game logic and settings.
     */
    public SettingsMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;

        // Load the hover sound effect from resources
        this.hoverSound = new AudioClip(getClass().getResource("/com/example/demo/music/menu-button-hover.wav").toExternalForm());
    }

    /**
     * Displays the settings menu with the available options such as sound toggle and navigation buttons.
     */
    public void show() {
        // Title for the settings menu
        Text settingsTitle = new Text("Settings");
        Font customFontSettings = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 30);
        settingsTitle.setFont(customFontSettings);
        settingsTitle.setStyle("-fx-font-weight: bold; -fx-fill: #FFFFFF;");

        // Back button to return to the start menu
        Button backButton = createTextButton("Back", customFontSettings);
        backButton.setOnAction(e -> goBackToStartMenu());

        // Button to navigate to the controls page
        Button controlsButton = createTextButton("Controls", customFontSettings);
        controlsButton.setOnAction(e -> goToControlsPage());

        // Toggle button for sound
        ToggleButton soundToggle = createSoundToggle(customFontSettings);
        addToggleButtonHoverEffects(soundToggle); // Add hover effects for the toggle button

        // VBox layout to arrange the elements vertically
        VBox layout = new VBox(20, settingsTitle, soundToggle, controlsButton, backButton);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Create and set the scene
        Scene settingsScene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(settingsScene);
    }

    /**
     * Creates a toggle button for controlling the sound (on/off).
     *
     * @param customFont The font to be used for the button text.
     * @return A ToggleButton for controlling the sound.
     */
    private ToggleButton createSoundToggle(Font customFont) {
        ToggleButton soundToggle = new ToggleButton();
        soundToggle.setFont(customFont);
        soundToggle.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");

        // Initialize toggle based on the Controller's sound state
        if (controller.isBackgroundMusicOn()) {
            soundToggle.setText("Sound: ON");
            soundToggle.setSelected(true);
        } else {
            soundToggle.setText("Sound: OFF");
            soundToggle.setSelected(false);
        }

        soundToggle.setOnAction(e -> {
            if (soundToggle.isSelected()) {
                soundToggle.setText("Sound: ON");
                controller.setBackgroundMusicOn(true);  // Update sound state
            } else {
                soundToggle.setText("Sound: OFF");
                controller.setBackgroundMusicOn(false);  // Update sound state
            }
        });

        return soundToggle;
    }

    /**
     * Creates a button with the given text and font.
     *
     * @param text The text displayed on the button.
     * @param font The font used for the button text.
     * @return A Button with the specified text and font.
     */
    private Button createTextButton(String text, Font font) {
        Button button = new Button(text);
        button.setFont(font);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
        addHoverEffects(button); // Add hover effects to regular buttons
        return button;
    }

    /**
     * Adds hover effects to a button such as changing text, style, and opacity.
     * Also plays a hover sound effect when the mouse enters the button.
     *
     * @param button The button to which the hover effects will be added.
     */
    private void addHoverEffects(Button button) {
        button.setOnMouseEntered(e -> {
            playHoverSound(); // Play sound when hovered
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

    /**
     * Adds hover effects to a toggle button such as changing style and opacity.
     * Also plays a hover sound effect when the mouse enters the toggle button.
     *
     * @param toggleButton The toggle button to which the hover effects will be added.
     */
    private void addToggleButtonHoverEffects(ToggleButton toggleButton) {
        toggleButton.setOnMouseEntered(e -> {
            playHoverSound(); // Play sound when hovered
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

    /**
     * Plays the hover sound effect when a button or toggle button is hovered over.
     */
    private void playHoverSound() {
        if (hoverSound != null) {
            hoverSound.play();
        }
    }

    /**
     * Navigates back to the start menu.
     */
    private void goBackToStartMenu() {
        StartMenu startMenu = new StartMenu(stage, screenWidth, screenHeight, controller);
        startMenu.show();
    }

    /**
     * Navigates to the controls page.
     */
    private void goToControlsPage() {
        ControlsMenu controlsMenu = new ControlsMenu(stage, screenWidth, screenHeight, controller);
        controlsMenu.show();
    }
}
