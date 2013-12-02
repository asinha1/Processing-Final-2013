import processing.core.*;
import biar.KeyReaderExample.KeyReader;

public class Main extends PApplet {
	public Field myField;
	public KeyReader keyReader;
	
	public void setup() {
		noCursor();
		size(800, 800);
		myField = new Field(this);
		keyReader = KeyReader.getInstance(this);
		frameRate(20);
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
		if (myField.loss == 1)
		{
			Field newField = new Field(this);
			myField = newField;
		}
	}
	
	public void keyPressed() {
		keyReader.onKeyPress();
	}
	
	public void keyReleased() {
		keyReader.onKeyRelease();
	}
}
