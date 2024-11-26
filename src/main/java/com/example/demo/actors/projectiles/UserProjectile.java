package com.example.demo.actors.projectiles;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "userfire.png";
	private static final int IMAGE_HEIGHT = 10;
	private static final int HORIZONTAL_VELOCITY = 15;
	private static final int HEALTH = 1;

	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, HEALTH);
	}

	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}
	
//	@Override
//	public void updateActor() {
//		updatePosition();
//	}
	
}
