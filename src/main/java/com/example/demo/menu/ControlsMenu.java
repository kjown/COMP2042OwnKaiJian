package com.example.demo.menu;

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
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

public class ControlsMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private final Controller controller;

    private List<Button> controlButtons;
    private int selectedIndex = 0; // Track the currently selected button index

    public ControlsMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
        this.controlButtons = new ArrayList<>();
    }

    // Display the controls menu
    public void show() {
        VBox controlsLayout = createControlsLayout();
        Scene controlsScene = new Scene(controlsLayout, screenWidth, screenHeight);
        stage.setScene(controlsScene);

        // Enable keyboard navigation
        controlsScene.setOnKeyPressed(e -> handleKeyPress(e.getCode()));
    }

    // Create the main layout for the controls menu
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
        controlButtons.add(backButton);
        controlsLayout.getChildren().add(backButton);

        return controlsLayout;
    }

    // Create a title text element
    private Text createTitle(String text, int fontSize) {
        Text title = new Text(text);
        title.setFont(loadCustomFont(fontSize));
        title.setFill(Color.WHITE);
        return title;
    }

    // Load the custom font with a specific size
    private Font loadCustomFont(int size) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), size);
    }

    // Add an image with an instruction label to the layout
    private void addControlInstruction(VBox layout, String imagePath, String instructionText) {
        ImageView imageView = loadControlImage(imagePath);
        Text instruction = createInstructionText(instructionText, 20);

        // Add image and instruction to layout
        layout.getChildren().addAll(imageView, instruction);
    }

    // Load the control image from a given path
    private ImageView loadControlImage(String imagePath) {
        Image controlImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(controlImage);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        return imageView;
    }

    // Create the instruction text with a custom font
    private Text createInstructionText(String text, int fontSize) {
        Text instruction = new Text(text);
        instruction.setFont(loadCustomFont(fontSize));
        instruction.setFill(Color.WHITE);
        return instruction;
    }

    // Create a button with custom text and action
    private Button createTextButton(String text, int fontSize, Runnable action) {
        Button button = new Button(text);
        button.setFont(loadCustomFont(fontSize));
        styleButton(button);
        addButtonHoverEffects(button);
        controlButtons.add(button);
        button.setOnAction(e -> action.run());
        return button;
    }

    // Apply styles to a button
    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: transparent; " +
                "-fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); " +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
    }

    // Add hover effects to a button
    private void addButtonHoverEffects(Button button) {
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #00FFFF, #FF00FF);");
            button.setOpacity(0.8);
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: #FF00FF;");
            button.setOpacity(1.0);
        });
        button.setOnMousePressed(e -> scaleButton(button, 0.95));
        button.setOnMouseReleased(e -> scaleButton(button, 1.0));
    }

    // Scale a button when pressed/released
    private void scaleButton(Button button, double scale) {
        button.setScaleX(scale);
        button.setScaleY(scale);
    }

    // Navigate back to the settings menu
    private void goBack() {
        SettingsMenu settingsMenu = new SettingsMenu(stage, screenWidth, screenHeight, controller);
        settingsMenu.show();
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
        selectedIndex = (selectedIndex > 0) ? selectedIndex - 1 : controlButtons.size() - 1;
        updateButtonStyles();
    }

    // Navigate down through the buttons
    private void navigateDown() {
        selectedIndex = (selectedIndex < controlButtons.size() - 1) ? selectedIndex + 1 : 0;
        updateButtonStyles();
    }

    // Select the currently highlighted button
    private void selectButton() {
        controlButtons.get(selectedIndex).fire();
    }

    // Update button styles based on selection
    private void updateButtonStyles() {
        for (int i = 0; i < controlButtons.size(); i++) {
            Button button = controlButtons.get(i);
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
}
