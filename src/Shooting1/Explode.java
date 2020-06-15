package Shooting1;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Explode extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 233L;
	private int x,y;
	private boolean ifLive = true;
	private Game_MainPanel_Shooting1 client;
	
	int[] Image_List = {4,7,13,18,26,34,49,30,15,5}; //��ըЧ��
	int Image_Index = 0;
	public Explode(int x, int y, Game_MainPanel_Shooting1 client)
	{
		this.x = x;
		this.y = y;
		this.client = client;
	}
			
	public void draw(Graphics g)
	{
		if(!ifLive) {
			client.explodrations.remove(this); 
		}
		if(Image_Index == Image_List.length){
			ifLive = false;
			Image_Index = 0;
		}
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, Image_List[Image_Index], Image_List[Image_Index]);
		g.setColor(c);
		
		Image_Index++;
	}
}

