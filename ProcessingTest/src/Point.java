import processing.core.*;

public class Point {
	public int x;
	public int y;
	public PApplet parent;
	
	public Point(PApplet parent, int x, int y)
	{
		this.parent = parent;
		this.x = x;
		this.y = y;
	}
	
	public void display(int c){
		int size = 8;
		for (int i = size * x; i < size * x + size; i++)
			for (int j = size * y; j < size * y + size; j++)
				parent.set(i, j, c);
	}
	
	public static boolean equalTo(Point A, Point B)
	{
		return (A.x == B.x || A.x == (B.x - 0))&& A.y == B.y;
	}
	
	public boolean outOfBounds()
	{
		return x < 0 || y < 0 || x > 79 || y > 79;
	}
	
}
