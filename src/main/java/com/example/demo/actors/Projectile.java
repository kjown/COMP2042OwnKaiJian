package com.example.demo.actors;

import com.example.demo.actors.ActiveActorDestructible;
import com.example.demo.controller.Main;

public abstract class Projectile extends ActiveActorDestructible {
	private int health;

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	@Override
	public abstract void updatePosition();

	public void updateActor() {
		updatePosition();
		if (outOfBounds()) {
			this.destroy();
		}
	}

	public boolean outOfBounds() {
		return getTranslateX() > Main.getScreenWidth();
	}

	private boolean healthAtZero() {
		return health == 0;
	}

	public int getHealth() {
		return health;
	}
}
