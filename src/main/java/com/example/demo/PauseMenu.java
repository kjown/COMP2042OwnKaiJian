package com.example.demo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.example.demo.controller.Main;

public class PauseMenu extends ImageView {
    private static final String IMAGE_NAME = "/com/example/demo/images/pauseMenu.png";
    private static final int WIDTH = 1300;
    private static final int HEIGHT = 300;
    public PauseMenu(double xPosition, double yPosition) {
        this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        this.setVisible(false);
        this.setFitHeight(HEIGHT);
        this.setFitWidth(WIDTH + 20);
        this.setLayoutX(0);
        this.setLayoutY(200);
    }
    public void showPauseMenu(){
        this.setVisible(true);
    }
    public void hidePauseMenu(){
        this.setVisible(false);
    }
}