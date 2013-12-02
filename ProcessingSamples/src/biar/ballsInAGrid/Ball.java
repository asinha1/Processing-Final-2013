package biar.ballsInAGrid;

import processing.core.*;
import java.util.Random;

public class Ball {
	private int x;
	private int y;
	private int diameter;
	private static PApplet parent;
	private static Random r;
	
	public Ball(PApplet parent, int x, int y, int diameter) {
		if (r == null) {
			r = new Random();
		}
		this.parent = parent;
		this.x = x;
		this.y = y;
		this.diameter = diameter;
	}
	
	public void render() {
		parent.fill(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		parent.ellipse(x, y, diameter, diameter);
	}
}
