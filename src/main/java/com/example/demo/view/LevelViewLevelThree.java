package com.example.demo.view;

import javafx.scene.Group;

public class LevelViewLevelThree extends LevelView {
    private final Group root;

    public LevelViewLevelThree(Group root, int heartsToDisplay) {
        super(root, heartsToDisplay);
        this.root = root;
    }

    public Group getRoot() {
        return root;
    }
}
