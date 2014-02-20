package view;

import java.awt.Graphics;

public class SnakePainter {

	private boolean hasPainted = false;
	private boolean paintsRegularly = false;

	public void paint(Graphics graphics) {
		graphics.drawLine(1000, 100, 100, 100);
		hasPainted = true;
	}

	public boolean hasPainted() {
		return hasPainted;
	}

	public boolean paintsRegularly() {
		return paintsRegularly;
	}

	public void paintRegularly(Graphics graphics) {
		paintEvery100Milliseconds(graphics);
		hasPainted = true;
	}

	private void paintEvery100Milliseconds(Graphics graphics) {
		SnakePaintingEvery100Milliseconds snakePaintingEvery100Milliseconds = new SnakePaintingEvery100Milliseconds(this, graphics);
		snakePaintingEvery100Milliseconds.start();
	}

	public void setPaintsRegularly(boolean paintsRegularly) {
		this.paintsRegularly = paintsRegularly;
	}

}