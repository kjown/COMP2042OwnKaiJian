package com.example.demo.view;

import com.example.demo.menu.PauseMenu;
import javafx.scene.Group;

/**
 * Represents the view for level two in the game.
 * This class manages the display of UI components specific to level two, including the heart display, win image, and pause menu.
 */
public class LevelViewLevelTwo {

    private static final double HEART_DISPLAY_X_POSITION = 5;
    private static final double HEART_DISPLAY_Y_POSITION = 25;
    private static final int WIN_IMAGE_X_POSITION = 355;
    private static final int WIN_IMAGE_Y_POSITION = 175;
    private static final int LOSS_SCREEN_X_POSITION = -160;
    private static final int LOSS_SCREEN_Y_POSISITION = -375;
    private static final int IMAGE_X_POSITION = 0;
    private static final int IMAGE_Y_POSITION = 0;
    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;

    private final Group root;
    private final WinImage winImage;
    private final HeartDisplay heartDisplay;
    private final PauseMenu pauseMenu;

    /**
     * Constructs a new LevelViewLevelTwo with the specified root group and number of hearts to display.
     *
     * @param root The root container for the level's scene.
     * @param heartsToDisplay The number of hearts to initially display.
     */
    public LevelViewLevelTwo(Group root, int heartsToDisplay) {
        this.root = root;
        this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
        this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
        this.pauseMenu = new PauseMenu(IMAGE_X_POSITION, IMAGE_Y_POSITION);
    }

    /**
     * Displays the heart display on the screen.
     */
    public void showHeartDisplay() {
        root.getChildren().add(heartDisplay.getContainer());
    }

    /**
     * Displays the win image on the screen.
     */
    public void showWinImage() {
        root.getChildren().add(winImage);
        winImage.showWinImage();
    }

    /**
     * Removes hearts from the display based on the remaining number of hearts.
     *
     * @param heartsRemaining The number of hearts that should remain on the display.
     */
    public void removeHearts(int heartsRemaining) {
        int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
        for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
            heartDisplay.removeHeart();
        }
    }

    /**
     * Toggles the visibility of the pause menu on the screen.
     */
    public void showPauseMenuImage() {
        if (!root.getChildren().contains(pauseMenu)) {
            root.getChildren().add(pauseMenu);
        } else {
            root.getChildren().remove(pauseMenu);
            root.getChildren().add(pauseMenu);
        }
        pauseMenu.showPauseMenu();
    }

    /**
     * Hides the pause menu from the screen.
     */
    public void hidePauseMenuImage() {
        pauseMenu.hidePauseMenu();
    }

    public HeartDisplay getHeartDisplay() {
        return heartDisplay;
    }

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }
}
