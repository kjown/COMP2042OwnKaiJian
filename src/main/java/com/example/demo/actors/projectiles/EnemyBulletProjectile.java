package com.example.demo.actors.projectiles;

import com.example.demo.actors.Projectile;

public class EnemyBulletProjectile extends Projectile {
    private static String IMAGE_NAME = "enemyBullet.png";
    private static int IMAGE_HEIGHT = 10;
    private static int HORIZONTAL_VELOCITY = -13;
    private static int HEALTH = 1;

    public EnemyBulletProjectile(double initialXPos, double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HEALTH);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }
}
