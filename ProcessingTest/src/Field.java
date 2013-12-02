import java.util.ArrayList;

import processing.core.*;
import ddf.minim.*;

public class Field {
	public PApplet parent;
	public Snake mySnake;
	public ArrayList<Point> foodPoints = new ArrayList<Point>();
	public ArrayList<Point> blockPoints = new ArrayList<Point>();
	private int step;
	public int loss;
    ArrayList<Platform> platforms = new ArrayList<Platform>(); 
    ArrayList<Coin> coins = new ArrayList<Coin>(); 
	public int won;
	Minim minim;
	AudioPlayer dieplayer;
	AudioPlayer coinplayer;
	AudioInput input;
    
	public Field(PApplet parent)
	{
		this.won = 0;
		this.parent = parent;
		
		minim = new Minim(parent);
		dieplayer = minim.loadFile("die.wav");
		coinplayer = minim.loadFile("coin.wav");
		
		mySnake = new Snake(parent, 40, 40);
		step = 0;
		loss = 0;
        /* Create Boundary Platforms */
        Platform floor = new Platform(parent,0,750,800,50); 
        platforms.add(floor); 
        Platform ceil = new Platform(parent,0,0,800,20); 
        platforms.add(ceil); 
        Platform leftWall = new Platform(parent,0,0,20,800); 
        platforms.add(leftWall); 
        Platform rightWall = new Platform(parent,780,0,20,800); 
        platforms.add(rightWall); 
          
        /* Create other Platforms (for maze) */
        Platform wall1 = new Platform(parent,20,85,370,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,370,85,20,600); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,460,85,20,330); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,460,480,370,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,460,395,370,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,460,85,250,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,690,85,20,250); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,550,315,160,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,550,170,20,165); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,550,170,70,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,460,480,20,180); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,700,480,20,270); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,460,660,170,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,100,665,290,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,100,185,20,500); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,100,185,200,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,280,185,20,300); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,280,555,20,130); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,280,555,110,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,180,465,120,20); 
        platforms.add(wall1); 
        wall1 = new Platform(parent,180,295,20,170); 
        platforms.add(wall1); 
          
        /* Coins */
          
        Coin temp = new Coin(parent,platforms, 40, 30); 
        coins.add(temp); 
        temp = new Coin(parent,platforms, 600, 240); 
        coins.add(temp); 
        temp = new Coin(parent,platforms, 600, 550); 
        coins.add(temp); 
        temp = new Coin(parent,platforms, 230, 375); 
        coins.add(temp); 
	}
	
	public void drawFood()
	{
		parent.fill(100);
		for (int i = 0; i < foodPoints.size(); i++)
		{
			foodPoints.get(i).display(0x339900);
		}
		
        for(int i = 0; i < platforms.size(); i++) { 
            platforms.get(i).render(); 
        } 
          
        for (int i = 0; i < coins.size(); i++) { 
            coins.get(i).render(); 
        } 
		
	}
	
	public void drawBlocks()
	{
		for (int i = 0; i < blockPoints.size(); i++)
		{
			blockPoints.get(i).display(0x993300);
		}
	}
	
	public void checkCollisions()
	{
		for (int i = 0; i < foodPoints.size(); i++)
		{
			if (Point.equalTo(mySnake.getHead(), foodPoints.get(i)))
			{
				foodPoints.remove(i);
				mySnake.grow(1);
				break;
			}
		}
	}
	
	public boolean checkBlockCollision()
	{
		for (int i = 0; i < blockPoints.size(); i++)
		{
			if (Point.equalTo(mySnake.getHead(), blockPoints.get(i)))
			{
				dieplayer.play();
				return true;
			}
		}
		
		//Check collisions with a platform
        for (int i = 0; i < platforms.size(); i++) { 
            Platform temp = platforms.get(i); 
            if(((temp.x < mySnake.getHead().x * 8) && (temp.x + temp.w > mySnake.getHead().x * 8)) && 
                    (temp.y + temp.h > mySnake.getHead().y * 8) && (mySnake.getHead().y * 8 + 8 > temp.y))
            {
                dieplayer.play();
            	return true; 
            }
        }
		return false;
	}
	
	public void coinCollide()
	{
		
        for(int i = 0; i < coins.size(); i++) { 
            Coin coin = coins.get(i); 
            if ((mySnake.getHead().x * 8 < coin.x + 10 && mySnake.getHead().x * 8 > coin.x - 10) && (mySnake.getHead().y * 8 < coin.y + 10 && mySnake.getHead().y * 8 > coin.y - 10)) 
            {
            	coinplayer.play();
            	coins.remove(i); 
            	return;
            }
        } 
	}
	
	public void moveField()
	{
		if (step == 1) {
		ArrayList<Point> newFoodPoints = new ArrayList<Point>();
		ArrayList<Point> newBlockPoints = new ArrayList<Point>();
		for (int i = 0; i < foodPoints.size(); i++)
		{
			Point oldPoint = foodPoints.get(i);
			Point newPoint = new Point(parent, oldPoint.x - 1, oldPoint.y);
			if (!newPoint.outOfBounds())
				newFoodPoints.add(newPoint);
		}
		foodPoints = newFoodPoints;
		
		for (int i = 0; i < blockPoints.size(); i++)
		{
			Point oldPoint = blockPoints.get(i);
			Point newPoint = new Point(parent, oldPoint.x - 1, oldPoint.y);
			if (!newPoint.outOfBounds())
				newBlockPoints.add(newPoint);
		}
		blockPoints = newBlockPoints;
		}
	}
	
	public void update()
	{
		// 1/3 chance of food appearing on a given draw
		step = (step + 1) % 2;
		checkCollisions();
		coinCollide();
		moveField();
		
		if (Math.random() < .3)
		{
			Point newFood = new Point(parent, 80 + (int)(Math.random() * 20), (int)(Math.random() * 100));
			
			foodPoints.add(newFood);
		}

		//5% chance of new blocks.
		if (Math.random() < .05)
		{
			Point newBlock = new Point(parent, 80 + (int)(Math.random() * 20), (int)(Math.random() * 100));
			
			blockPoints.add(newBlock);
		}
		
		mySnake.move();
	}
	
	public void drawStuff()
	{

		
		if (coins.size() == 0)
		{
			parent.background(0xffffff);
			parent.textSize(32);
			parent.fill(0);
			parent.text("You win!", 300, 300);
			parent.text("Final Score:" + mySnake.getScore(), 300, 350);
			parent.text("Press R to restart",300,400);
			won = 1;
		}
		else if (!(mySnake.checkBodyCollision() || mySnake.getHead().outOfBounds() || checkBlockCollision()))
		{
			update();
			drawFood();
			drawBlocks();
			mySnake.display();
			parent.fill(0);
			parent.textSize(30);
			parent.text("Score:" + mySnake.getScore(), 20, 740);
		}
		else
		{
			parent.background(0);
			loss = 1;
		}
	}
}
