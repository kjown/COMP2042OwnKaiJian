package com.example.demo;

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

public class ControlsMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private final Controller controller;

    public ControlsMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
    }

    public void show() {
        // Title for controls
        Text controlsTitle = new Text("Controls");
        Font customFontSettings = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 30);
        controlsTitle.setFont(customFontSettings);
        controlsTitle.setFill(Color.WHITE);

        // Create control instructions
        VBox controlsLayout = new VBox(20);
        controlsLayout.setAlignment(Pos.CENTER);
        controlsLayout.getChildren().add(controlsTitle);

        // Add control images and instructions
        addControlInstruction(controlsLayout, "/com/example/demo/images/SPACE.png", "SPACE - Shoot");
        addControlInstruction(controlsLayout, "/com/example/demo/images/ARROWUP.png", "UP ARROW - Go Up");
        addControlInstruction(controlsLayout, "/com/example/demo/images/ARROWDOWN.png", "DOWN ARROW - Go Down");
        addControlInstruction(controlsLayout, "/com/example/demo/images/ARROWLEFT.png", "LEFT ARROW - Go Left");
        addControlInstruction(controlsLayout, "/com/example/demo/images/ARROWRIGHT.png", "RIGHT ARROW - Go Right");
        addControlInstruction(controlsLayout, "/com/example/demo/images/ESC.png", "ESC - PAUSE");


        // Create back button
        Button backButton = createTextButton("Back");
        backButton.setOnAction(e -> goBack());
        controlsLayout.getChildren().add(backButton);

        // Set layout properties
        controlsLayout.setPadding(new Insets(20));
        controlsLayout.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        // Create and set scene
        Scene controlsScene = new Scene(controlsLayout, screenWidth, screenHeight);
        stage.setScene(controlsScene);
    }

    private void addControlInstruction(VBox layout, String imagePath, String instructionText) {
        // Load image
        Image controlImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(controlImage);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);

        // Create instruction text
        Text instruction = new Text(instructionText);
        Font customFontSettings = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 20);
        instruction.setFont(customFontSettings);
        instruction.setFill(Color.WHITE);

        // Add image and instruction to layout
        layout.getChildren().addAll(imageView, instruction);
    }

    private Button createTextButton(String text) {
        Button button = new Button(text);
        Font customFontSettings = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 50);
        button.setFont(customFontSettings);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #FF00FF, #00FFFF, #FF00FF); -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 3, 0.0, 2, 2);");
        addButtonHoverEffects(button);
        return button;
    }

    private void addButtonHoverEffects(Button button) {
        button.setOnMouseEntered(e -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: linear-gradient(from 0% 0% to 0% 100%, #00FFFF, #FF00FF);");
            button.setOpacity(0.8);
        });
        button.setOnMouseExited(e -> {
            button.setStyle("-fx-background-color: transparent; -fx-text-fill: #FF00FF;");
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

    private void goBack() {
        // Navigate back to SettingsPage
        SettingsMenu settingsMenu = new SettingsMenu(stage, screenWidth, screenHeight, controller);
        settingsMenu.show();
    }
}
