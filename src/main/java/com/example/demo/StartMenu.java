package com.example.demo;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.demo.controller.Controller;
import javafx.scene.text.Font;

public class StartMenu {
    private final Stage stage;
    private final int screenWidth;
    private final int screenHeight;
    private final Controller controller;

    public StartMenu(Stage stage, int screenWidth, int screenHeight, Controller controller) {
        this.stage = stage;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.controller = controller;
    }

    public void show() {
        Text title = new Text("Sky Battle");
        title.setStyle("-fx-font-size: 100px; -fx-font-weight: bold; -fx-fill: linear-gradient(from 0% 0% to 0% 100%, #B0C4DE, #DADBDD, #FDDC5C); -fx-stroke-width: 1px; -fx-effect: dropshadow(three-pass-box, rgba(255,255,255,0.8), 10, 0.0, 0, 0);");
        // Load custom font
        Font retroFont = Font.loadFont(getClass().getResourceAsStream("/com/example/demo/fonts/PressStart2P-Regular.ttf"), 50);
        title.setFont(retroFont);

        // Load images and set them to the buttons
        Button startButton = createImageButton("Start Button.png");
        Button settingsButton = createImageButton("Settings Button.png");
        Button exitButton = createImageButton("Exit Button.png");

        // Add actions for each button
        startButton.setOnAction(e -> startGame());
        settingsButton.setOnAction(e -> showSettings());
        exitButton.setOnAction(e -> stage.close());

        // Button hover effects
        addHoverEffects(startButton);
        addHoverEffects(settingsButton);
        addHoverEffects(exitButton);

        VBox layout = new VBox(20, title, startButton, settingsButton, exitButton);
        layout.setAlignment(Pos.CENTER);

        // Load and set background image
        layout.setBackground(new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("/com/example/demo/images/start-menu-background.png")),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        Scene scene = new Scene(layout, screenWidth, screenHeight);
        stage.setScene(scene);
    }

    private Button createImageButton(String imagePath) {
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/com/example/demo/images/" + imagePath)));
        imageView.setFitWidth(200);  // Set appropriate width
        imageView.setFitHeight(50);  // Set appropriate height
        Button button = new Button();
        button.setGraphic(imageView); // Set the image as the graphic for the button
        button.setStyle("-fx-background-color: transparent;"); // Transparent background
        return button;
    }

    private void startGame() {
        try {
            controller.launchGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSettings() {
        // Code to show settings
        System.out.println("Showing settings...");
    }
    private void addHoverEffects(Button button) {
        button.setOnMouseEntered(e -> {
            button.setOpacity(0.8); // Change opacity on hover
        });
        button.setOnMouseExited(e -> {
            button.setOpacity(1.0); // Reset opacity
        });
        button.setOnMousePressed(e -> {
            button.setScaleX(0.95); // Slightly scale down on click
            button.setScaleY(0.95);
        });
        button.setOnMouseReleased(e -> {
            button.setScaleX(1.0); // Reset scale
            button.setScaleY(1.0);
        });
    }
}

