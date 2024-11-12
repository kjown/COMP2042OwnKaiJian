package com.example.demo.actors;

public class Enemy2 extends Helicopter {
    private static final String IMAGE_NAME = "enemy2.png";
    private static final int IMAGE_HEIGHT = 50;
    private static final int HORIZONTAL_VELOCITY = -3;
    private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
    private static final int INITIAL_HEALTH = 2;
    private static final double FIRE_RATE = .03;

    public Enemy2(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
    }

}


