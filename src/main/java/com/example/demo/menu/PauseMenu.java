package com.example.demo.menu;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.demo.controller.Main;

/**
 * The PauseMenu class represents the pause menu in the game.
 * It is displayed as an image and can be shown or hidden based on the game state.
 */
public class PauseMenu extends ImageView {

    // Path to the pause menu image file
    private static final String IMAGE_NAME = "/com/example/demo/images/pauseMenu.png";

    // Width and height for the pause menu
    private static final int WIDTH = 1300;
    private static final int HEIGHT = 300;

    /**
     * Constructor to initialize the PauseMenu with a specific position.
     *
     * @param xPosition The X position where the pause menu will be displayed.
     * @param yPosition The Y position where the pause menu will be displayed.
     */
    public PauseMenu(double xPosition, double yPosition) {
        // Set the image for the pause menu from the specified resource path
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));

        // Initially set the pause menu to be invisible
        this.setVisible(false);

        // Set the width and height of the pause menu image
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH + 20);  // Added 20 for additional padding/adjustment

        // Set the layout position for the pause menu
        this.setLayoutX(xPosition);
        this.setLayoutY(yPosition);
    }

    /**
     * Makes the pause menu visible on the screen.
     * This method can be called to display the pause menu when the game is paused.
     */
    public void showPauseMenu() {
        this.setVisible(true);
    }

    /**
     * Hides the pause menu from the screen.
     * This method can be called to remove the pause menu when the game is resumed.
     */
    public void hidePauseMenu() {
        this.setVisible(false);
    }
}
