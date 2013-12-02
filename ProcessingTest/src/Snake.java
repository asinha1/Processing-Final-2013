import java.util.ArrayList;

import processing.core.*;

public class Snake {
	private PApplet parent;
	private Point head;
	private int direction = 1; //direction: 1 = down, 2 = left, 3 = right, 4 = up
	ArrayList<Point> body = new ArrayList<Point>();
	
	public Snake(PApplet parent, int x, int y)
	{
		this.parent = parent;
		this.head = new Point(parent, x, y);
	}
	
	public Point getHead()
	{
		return head;
	}
	
	public void grow(int i)
	{
		for (int k = 0; k < i; k++)
			body.add(head);
	}
	
	public void move()
	{
		if (body.size() > 0)
			body.remove(0);
		Point newHead = head;
		if (direction == 1)
			newHead = new Point(parent, head.x, head.y - 1);
		else if (direction == 2)
			newHead = new Point(parent, head.x - 1, head.y);
		else if (direction == 3)
			newHead = new Point(parent, head.x + 1, head.y);
		else if (direction == 4)
			newHead = new Point(parent, head.x, head.y + 1);
		
		body.add(head);
		head = newHead;
	}
	
	public boolean checkBodyCollision()
	{
		for (int i = 0; i < body.size() - 1; i++)
		{
			if (Point.equalTo(body.get(i), head))
				return true;
		}
		return false;
	}
	public void setDirection(int x)
	{
		this.direction = x;
	}
	
	public void display()
	{
		for (int i = 0; i < body.size(); i++)
			body.get(i).display(0);
	}
}
