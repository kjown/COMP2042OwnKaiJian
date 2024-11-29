package com.example.demo.actors;

/**
 * The Destructible interface represents an entity in the game that can take damage and be destroyed.
 * Any actor or object that can be destroyed should implement this interface to define the behavior of damage and destruction.
 */
public interface Destructible {

	/**
	 * Method to handle the action of taking damage.
	 * This method should define how the object or actor responds to taking damage,
	 * such as reducing health, triggering an animation, or initiating a destruction process.
	 */
	void takeDamage();

	/**
	 * Method to destroy the object or actor.
	 * This should mark the entity as destroyed, preventing it from further actions or interactions in the game.
	 */
	void destroy();
}
