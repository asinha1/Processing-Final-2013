package hellstern.PolicemanExample;

import processing.core.*;

public class Policeman
{
	public PVector origin;
	public PVector speed;
	Sprite spr;
	Flashlight light;
	private Main parent;
	  
	public Policeman(Main parent, PVector origin)
	{
		this.parent = parent;
	    this.origin = origin;
	    spr = new Sprite(parent, "policeman_strip.png", 4);
	    light = new Flashlight(parent);
	    speed = new PVector(0, 0);
	}
	  
	void update()
	{
		KeyReader r = parent.reader;
		/* Change hspeed and vspeed based on which keys are pressed */
		int runFactor = r.check("SHIFT")? 2: 1;
		speed.x = runFactor * (r.check("D")? 2: r.check("A")? -2: 0);
		speed.y = runFactor * (r.check("S")? 2: r.check("W")? -2: 0);

		/* Animate the sprite if moving */
		if (speed.x != 0 || speed.y != 0) {
			spr.speed = .25f;
		} else {
			spr.speed = 0;
			spr.frame = 1;
		}
		
		/* Set the direction of rotation */
		spr.angle = parent.atan2(parent.mouseY - origin.y, parent.mouseX - origin.x);
		
		/* Increment x and y */
		origin.add(speed);
	}
  
	void render()
	{
		update();
		light.render(origin);
		spr.render(origin);
	}
}
