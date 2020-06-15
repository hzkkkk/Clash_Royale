package Shooting1;

import java.awt.Graphics;
import java.awt.Rectangle;

public class CollidingObjects {
	private  int Width;
	private  int Height;
	public  int x,y;
	Game_MainPanel_Shooting1 client;
	
	public CollidingObjects(int x, int y, int width, int height, Game_MainPanel_Shooting1 client) {
		Width = width;
		Height = height;
		this.x = x;		
		this.y = y;
		this.client = client;
	}

	public void draw(Graphics g)
	{
		g.fillRect(x, y, Width, Height);
	}
	
	public Rectangle get_Rect()
	{
		return new Rectangle(x, y, Width, Height);
	}
	
	public  int get_Width()
	{
		return Width;
	}
	
	public  int get_Height()
	{
		return Height;
	}
}
