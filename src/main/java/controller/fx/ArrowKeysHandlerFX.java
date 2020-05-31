package controller.fx;

import applicationBoundary.SnakeGameLogger;
import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;
import static model.Direction.*;

public class ArrowKeysHandlerFX implements EventHandler<javafx.scene.input.KeyEvent> {

	private Controller controller;

	public ArrowKeysHandlerFX(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void handle(javafx.scene.input.KeyEvent keyEvent) {
		String message = "The key " + keyEvent.getCode() + " has been pressed";
		SnakeGameLogger.info(this, message);

		if (keyEvent.getCode() == KeyCode.DOWN)
			controller.setDirectionOfSnakeMovement(DOWN);
		if (keyEvent.getCode() == KeyCode.UP)
			controller.setDirectionOfSnakeMovement(UP);
		if (keyEvent.getCode() == KeyCode.RIGHT)
			controller.setDirectionOfSnakeMovement(RIGHT);
		if (keyEvent.getCode() == KeyCode.LEFT)
			controller.setDirectionOfSnakeMovement(LEFT);
	}
}
