import processing.core.*;
import biar.KeyReaderExample.KeyReader;

public class Main extends PApplet {
	public Field myField;
	public KeyReader keyReader;
	
	public void setup() {
		size(640, 640);
		myField = new Field(this);
		keyReader = KeyReader.getInstance(this);
		frameRate(30);
	}
	
	public void draw() {
		clear();


		
		//background(0x339900);
		background(0xffffff);
		if (keyReader.check("W"))
		{
			myField.mySnake.setDirection(1);
		}
		if (keyReader.check("A"))
			myField.mySnake.setDirection(2);
		if (keyReader.check("S"))
			myField.mySnake.setDirection(4);
		if (keyReader.check("D"))
			myField.mySnake.setDirection(3);
		myField.drawStuff();
	}
	
	public void keyPressed() {
		keyReader.onKeyPress();
	}
	
	public void keyReleased() {
		keyReader.onKeyRelease();
	}
}
