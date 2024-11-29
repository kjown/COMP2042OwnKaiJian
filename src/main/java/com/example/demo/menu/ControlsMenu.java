package com.example.demo.menu;

import com.example.demo.controller.AudioManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.demo.controller.Controller;

/**
 * Represents the controls menu in the game, where players can view the control instructions.
 * The menu includes a list of controls with corresponding images and actions, and a back button
 * to return to the previous menu.
 */
public class ControlsMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private final Controller controller;

    /**
     * Constructs a ControlsMenu object to display the game control instructions.
     *
     * @param stage The primary stage for rendering the menu.
     * @param screenWidth The width of the screen.
     * @param screenHeight The height of the screen.
     * @param controller The game controller managing the menu actions.
     */
    public ControlsMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
    }

    /**
     * Displays the controls menu.
     */
    public void show() {
        VBox controlsLayout = createControlsLayout();
        Scene controlsScene = new Scene(controlsLayout, screenWidth, screenHeight);
        stage.setScene(controlsScene);
    }

    /**
     * Creates the main layout for the controls menu, including instructions and a back button.
     *
     * @return The VBox layout containing control instructions and buttons.
     */
    private VBox createControlsLayout() {
        Text controlsTitle = createTitle("Controls", 30);
        VBox controlsLayout = new VBox(20, controlsTitle);
        controlsLayout.setAlignment(Pos.CENTER);
        controlsLayout.setPadding(new Insets(20));
        controlsLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Add control images and instructions
        addControlInstruction(controlsLayout, "/com/example/demo/images/SPACE.png", "SPACE - Shoot");
        addControlInstruction(controlsLayout, "/com/example/demo/images/ARROWUP.png", "UP ARROW - Go Up");
        addControlInstruction(controlsLayout, "/com/example/demo/images/ARROWDOWN.png", "DOWN ARROW - Go Down");
        addControlInstruction(controlsLayout, "/com/example/demo/images/ARROWLEFT.png", "LEFT ARROW - Go Left");
        addControlInstruction(controlsLayout, "/com/example/demo/images/ARROWRIGHT.png", "RIGHT ARROW - Go Right");

        // Add back button
        Button backButton = createTextButton("Back", 50, this::goBack);
        controlsLayout.getChildren().add(backButton);

        return controlsLayout;
    }

    /**
     * Creates a title text element with custom font and color.
     *
     * @param text The text to display in the title.
     * @param fontSize The font size of the title.
     * @return A Text object representing the title.
     */
    private Text createTitle(String text, int fontSize) {
        Text title = new Text(text);
        title.setFont(loadCustomFont(fontSize));
        title.setFill(Color.WHITE);
        return title;
    }

    /**
     * Loads a custom font for text elements.
     *
     * @param size The font size to load.
     * @return The Font object loaded from the resources.
     */
    private Font loadCustomFont(int size) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), size);
    }

    /**
     * Adds an image with an instruction label to the controls menu layout.
     *
     * @param layout The VBox layout to add the image and instruction to.
     * @param imagePath The path to the image file.
     * @param instructionText The instruction text to display alongside the image.
     */
    private void addControlInstruction(VBox layout, String imagePath, String instructionText) {
        ImageView imageView = loadControlImage(imagePath);
        Text instruction = createInstructionText(instructionText, 20);

        // Add image and instruction to layout
        layout.getChildren().addAll(imageView, instruction);
    }

    /**
     * Loads a control image from the specified file path.
     *
     * @param imagePath The path to the image file.
     * @return An ImageView object representing the loaded image.
     */
    private ImageView loadControlImage(String imagePath) {
        Image controlImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(controlImage);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        return imageView;
    }

    /**
     * Creates an instruction text element with custom font and color.
     *
     * @param text The instruction text to display.
     * @param fontSize The font size of the instruction text.
     * @return A Text object representing the instruction.
     */
    private Text createInstructionText(String text, int fontSize) {
        Text instruction = new Text(text);
        instruction.setFont(loadCustomFont(fontSize));
        instruction.setFill(Color.WHITE);
        return instruction;
    }

    /**
     * Creates a button with custom text and an action to perform when clicked.
     *
     * @param text The text displayed on the button.
     * @param fontSize The font size of the button text.
     * @param action The action to perform when the button is clicked.
     * @return A Button object with custom style and action.
     */
    private Button createTextButton(String text, int fontSize, Runnable action) {
        Button button = new Button(text);
        button.setFont(loadCustomFont(fontSize));
        styleButton(button);
        addButtonHoverEffects(button);
        button.setOnAction(e -> action.run());
        return button;
    }

    /**
     * Applies custom styling to a button.
     *
     * @param button The button to style.
     */
    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
    }

    /**
     * Adds hover effects to a button, including changing the button's color and playing a sound effect.
     *
     * @param button The button to add hover effects to.
     */
    private void addButtonHoverEffects(Button button) {
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #00FFFF, #FF00FF);");
            button.setOpacity(0.8);
            AudioManager.getInstance().playSoundEffect("/com/example/demo/music/menu-button-hover.wav");
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: #FF00FF;");
            button.setOpacity(1.0);
        });
        button.setOnMousePressed(e -> scaleButton(button, 0.95));
        button.setOnMouseReleased(e -> scaleButton(button, 1.0));
    }

    /**
     * Scales the size of a button when pressed or released to give a visual feedback effect.
     *
     * @param button The button to scale.
     * @param scale The scale factor to apply.
     */
    private void scaleButton(Button button, double scale) {
        button.setScaleX(scale);
        button.setScaleY(scale);
    }

    /**
     * Navigates back to the settings menu when the back button is clicked.
     */
    private void goBack() {
        SettingsMenu settingsMenu = new SettingsMenu(stage, screenWidth, screenHeight, controller);
        settingsMenu.show();
    }
}
