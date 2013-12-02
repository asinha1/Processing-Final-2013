package hellstern.PolicemanExample;

import processing.core.*;

public class Main extends PApplet {
	
	public KeyReader reader;
	private Policeman man;
	public PImage bg;
	
	public void setup()
	{
	    noCursor();
	    smooth();
	  
	    /* Initialize key reader */
	    reader = KeyReader.getInstance(this);
	  
	    bg = loadImage("concrete.jpg");
	    size(bg.width, bg.height);
	  
	    /* Initialize player */
	    man = new Policeman(this, new PVector(width/2, height/2));
	}
	
	public void draw()
	{
	  man.render();
	  
	  /* Draw cursor */
	  strokeWeight(2);
	  stroke(0xffff00);
	  line(mouseX - 2, mouseY - 2, mouseX + 2, mouseY + 2);
	  line(mouseX - 2, mouseY + 2, mouseX + 2, mouseY - 2);
	}
	
	public void keyPressed()
	{
	  reader.onKeyPress();
	}
	
	public void keyReleased()
	{
	  reader.onKeyRelease();
	}
}