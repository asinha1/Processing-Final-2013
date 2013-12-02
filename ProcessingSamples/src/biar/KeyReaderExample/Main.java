package biar.KeyReaderExample;

import processing.core.*;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 * This is a class that demonstrates the use of Andy Biar's KeyReader for Processing
 * This KeyReader is a modification of an earlier version by Dustin and Karl Hellstern.
 * @author Andy Biar
 *
 */
public class Main extends PApplet{
	// Declare a KeyReader as a field
	private static KeyReader keyReader;

	public void setup() {
		// Then just call KeyReader.getInstance(PApplet, boolean) to initialize the KeyReader
		// (If you pass true, the KeyReader will print out the full list of available keys to the console,
		// which can be useful in the development stage.)
		keyReader = KeyReader.getInstance(this, true);
		
		textAlign(CENTER);
		size(100, 100);
		background(0x000000);
	}
	
	public void draw() {
		clear();
		int numKeysPressed = 0;
		
		/* 
		 * There are two ways to check for a key press on this KeyReader.
		 * The first way is used to ask the KeyReader about a specific key.
		 * This will probably be how you want to use this in your game. 
		 */
		
		// if presssing a, make background red
		if (keyReader.check("A")) {
			background(0xff0000);
		}
		// else if pressing left arrow key, make background green
		else if (keyReader.check("left")) { // works the for upper or lowercase letters
			background(0x00ff00);
		}
		// else if pressing space make background blue
		else if (keyReader.check("SPACE")) {
			background(0, 0, 255);
		}
		// else make background black
		else background(0x000000);
		
		
		/* 
		 * The other way is to check the integer key code that corresponds to the key.
		 * Using the loop below, I check all key codes from 0 to 255 and print
		 * to the screen which keys are being pressed.
		 */
		for (int i = 0; i < 200; i++) {
			Boolean ithKeyPressed = keyReader.check(i);
			if (ithKeyPressed) {
				this.text("Pressing " + KeyEvent.getKeyText(i), 
						(numKeysPressed / 30) * 20 + 50, (numKeysPressed % 30) * 10 + 20);
				numKeysPressed++;
			}
		}
	}
	
	// IMPORTANT: You must declare these functions below exactly like this to integrate with Processing
	
	public void keyPressed() {
		keyReader.onKeyPress();
	}
	
	public void keyReleased() {
		keyReader.onKeyRelease();
	}
}
