package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DownKeyListener implements KeyListener {

	private Controller controller;

	public DownKeyListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("in keyPressed");
		controller.goDown();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}