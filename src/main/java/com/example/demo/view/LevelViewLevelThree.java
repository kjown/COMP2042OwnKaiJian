package com.example.demo.view;

import javafx.scene.Group;

/**
 * Represents the view for level three in the game.
 * This class extends {@link LevelView} and is used for managing the display of UI components specific to level three.
 */
public class LevelViewLevelThree extends LevelView {

    private final Group root;

    /**
     * Constructs a new LevelViewLevelThree with the specified root group and the number of hearts to display.
     *
     * @param root The root container for the level's scene.
     * @param heartsToDisplay The number of hearts to initially display.
     */
    public LevelViewLevelThree(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
    }

    /**
     * Returns the root container for level three.
     *
     * @return The root {@link Group} for the level.
     */
    public Group getRoot() {
        return root;
    }
}
