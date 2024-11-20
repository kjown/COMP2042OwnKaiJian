package com.example.demo.actors;

import com.example.demo.controller.AudioManager;

public abstract class Helicopter extends ActiveActorDestructible {

    public int health;

    public Helicopter(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
        super(imageName, imageHeight, initialXPos, initialYPos);
        this.health = health;
    }

    public abstract ActiveActorDestructible fireProjectile();

    @Override
    public void takeDamage() {
        health--;
        // Play hit marker sound
        AudioManager.getInstance().playSoundEffect("/com/example/demo/music/hitmarker.mp3");

        if (healthAtZero()) {
            this.destroy();
        }
    }

    protected double getProjectileXPosition(double xPositionOffset) {
        return getLayoutX() + getTranslateX() + xPositionOffset;
    }

    protected double getProjectileYPosition(double yPositionOffset) {
        return getLayoutY() + getTranslateY() + yPositionOffset;
    }

    private boolean healthAtZero() {
        return health == 0;
    }

    public int getHealth() {
        return health;
    }
}