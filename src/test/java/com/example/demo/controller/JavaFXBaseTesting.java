    // JavaFXBaseTesting.java
    package com.example.demo.controller;

    import javafx.application.Platform;
    import org.junit.jupiter.api.extension.BeforeAllCallback;
    import org.junit.jupiter.api.extension.ExtensionContext;

    import java.util.concurrent.CountDownLatch;

    public class JavaFXBaseTesting implements BeforeAllCallback {
        private static boolean javafxInitialized = false;

        @Override
        public void beforeAll(ExtensionContext context) throws InterruptedException {
            if (!javafxInitialized) {
                // CountDownLatch to ensure JavaFX startup is complete
                CountDownLatch latch = new CountDownLatch(1);

                // Starting JavaFX
                Platform.startup(() -> {});

                // Wait for JavaFX to start and initialize before proceeding
                Platform.runLater(latch::countDown);
                latch.await();

                javafxInitialized = true;
            }
        }
    }
