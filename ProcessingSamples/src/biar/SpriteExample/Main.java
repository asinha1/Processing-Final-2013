package biar.SpriteExample;

import processing.core.*;

public class Main extends PApplet {
	Sprite pokeRight;
	int location;
	
	public void setup() {
		size(400, 400);
		location = 200;
		pokeRight = new Sprite(this, "pokeRight.png", 3);
	}
	
	public void draw() {
		clear();
		
		location += 2;
		if (location > width) location = 0;
		
		pokeRight.update();
		pokeRight.render(location, 200);
	}
}
