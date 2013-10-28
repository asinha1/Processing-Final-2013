import java.util.ArrayList;

import processing.core.*;

public class Field {
	public PApplet parent;
	public Snake mySnake;
	public ArrayList<Point> foodPoints = new ArrayList<Point>();
	public ArrayList<Point> blockPoints = new ArrayList<Point>();
	private int step;
	public int loss;
	
	public Field(PApplet parent)
	{
		this.parent = parent;
		mySnake = new Snake(parent, 40, 40);
		step = 0;
		loss = 0;
	}
	
	public void drawFood()
	{
		for (int i = 0; i < foodPoints.size(); i++)
		{
			foodPoints.get(i).display(0x339900);
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
				return true;
			}
		}
		return false;
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
		moveField();
		
		if (Math.random() < .3)
		{
			Point newFood = new Point(parent, 60 + (int)(Math.random() * 20), (int)(Math.random() * 80));
			
			foodPoints.add(newFood);
		}

		//5% chance of new blocks.
		if (Math.random() < .05)
		{
			Point newBlock = new Point(parent, 60 + (int)(Math.random() * 20), (int)(Math.random() * 80));
			
			blockPoints.add(newBlock);
		}
		
		mySnake.move();
	}
	
	public void drawStuff()
	{
		if (!(mySnake.checkBodyCollision() || mySnake.getHead().outOfBounds() || checkBlockCollision()))
		{
			update();
			drawFood();
			drawBlocks();
			mySnake.display();
		}
		else
		{
			parent.background(0);
			loss = 1;
		}
	}
}
