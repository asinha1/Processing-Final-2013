package biar.ballsInAGrid;

import processing.core.*;
import java.util.ArrayList;

public class Main extends PApplet {
	ArrayList<Ball> balls = new ArrayList<Ball>();

	public void setup() {
		size(400, 400);
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				balls.add(new Ball(this, i * 8 + 4, j * 8 + 4, 6));
			}
		}
	}
	
	public void draw() {
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).render();
		}
	}
}
