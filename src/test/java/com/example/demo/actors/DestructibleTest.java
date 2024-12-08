package com.example.demo.actors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestructibleTest {

    private static class TestDestructibleActor implements Destructible {
        private boolean destroyed = false;

        @Override
        public void takeDamage() {
            destroy();
        }

        @Override
        public void destroy() {
            destroyed = true;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }

    private TestDestructibleActor actor;

    @BeforeEach
    void setUp() {
        actor = new TestDestructibleActor();
    }

    @Test
    void testTakeDamage() {
        actor.takeDamage();
        assertTrue(actor.isDestroyed(), "Actor should be destroyed after taking damage");
    }

    @Test
    void testDestroy() {
        actor.destroy();
        assertTrue(actor.isDestroyed(), "Actor should be destroyed");
    }
}