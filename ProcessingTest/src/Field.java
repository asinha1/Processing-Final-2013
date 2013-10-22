import java.util.ArrayList;

import processing.core.*;

public class Field {
	public PApplet parent;
	public Snake mySnake;
	public ArrayList<Point> foodPoints = new ArrayList<Point>();
	
	public Field(PApplet parent)
	{
		this.parent = parent;
		mySnake = new Snake(parent, 40, 40);
		
	}
	
	public void drawFood()
	{
		for (int i = 0; i < foodPoints.size(); i++)
		{
			foodPoints.get(i).display(0x339900);
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
	
	public void moveField()
	{
		ArrayList<Point> newFoodPoints = new ArrayList<Point>();
		for (int i = 0; i < foodPoints.size(); i++)
		{
			Point oldPoint = foodPoints.get(i);
			Point newPoint = new Point(parent, oldPoint.x - 1, oldPoint.y);
			if (!newPoint.outOfBounds())
				newFoodPoints.add(newPoint);
		}
		foodPoints = newFoodPoints;
	}
	
	public void update()
	{
		// 1/3 chance of food appearing on a given draw

		checkCollisions();
		moveField();
		
		if (Math.random() < .3)
		{
			Point newFood = new Point(parent, 60 + (int)(Math.random() * 20), (int)(Math.random() * 80));
			
			foodPoints.add(newFood);
		}

		mySnake.move();
	}
	
	public void drawStuff()
	{
		if (!(mySnake.checkBodyCollision() || mySnake.getHead().outOfBounds()))
		{
			update();
			drawFood();
			mySnake.display();
		}
		else
			parent.background(0);
	}
}
