package Shooting1;


import java.awt.Graphics;


import javax.swing.ImageIcon;

import ShareConstant.Direction;

public class Bullet2 extends Bullets {
	 /*---------------------------------绘制--------------------------------*/
	public Bullet2(int x, int y, Direction dir, Game_MainPanel_Shooting1 client) {
		super(x, y, dir, client);
		ImageIcon icon = new ImageIcon(getClass().getResource("image/wall.gif"));
		BulletsImage = icon.getImage();
		if( BulletsImage == null ) 
			System.out.println("roleImage == null");
	}
	
	protected void draw (Graphics g){
		if(!(getLive()))
		{
			try {
				client.bullets2.remove(this);
			}catch(Exception e){
				e.printStackTrace();
			}
			return ;
		}
		g.drawImage( BulletsImage, x, y, null);
		move();
	}
	/*--------------------------------绘制--------------------------------*/
	
	  /*--------------------------------检测--------------------------------*/
	public boolean ifCollideWithPlayer1(Player1 player1)
	{
		if(this.get_Rect().intersects(player1.get_Rect()) 
				&& player1.ifLive()==true )
		{
			this.ifLive = false; 
			if(dir.equals(Direction.L))
			{
				player1.x_speed=-40;
				player1.y_speed=-14;
			}
			if(dir.equals(Direction.R))
			{
				player1.x_speed=40;
				player1.y_speed=-14;
			}
			Explode e = new Explode(x, y, client); 
			//������ըЧ��
			client.explodrations.add(e);
			//Ѫ��������Ѫ
			player1.game_Life -= 1;
			player1.Infinity_Count++;
		}
		return false;
	}
	
	  /*--------------------------------检测--------------------------------*/
	
//	private static Image[] bulletImages = null;
//	private static Map<String, Image> imgs = new HashMap<String, Image>(); // ����Map��ֵ�ԣ��ǲ�ͬ�����Ӧ��ͬ�ĵ�ͷ
//
//	static
//	{
//		bulletImages = new Image[]
//			{ // ��ͬ������ӵ�
//				tk.getImage(Bullet.class.getClassLoader().getResource(
//						"images/bulletL.gif")),
//
//				tk.getImage(Bullet.class.getClassLoader().getResource(
//						"images/bulletU.gif")),
//
//				tk.getImage(Bullet.class.getClassLoader().getResource(
//						"images/bulletR.gif")),
//
//				tk.getImage(Bullet.class.getClassLoader().getResource(
//						"images/bulletD.gif")),
//
//			};
//		imgs.put("L", bulletImages[0]); //  加入Map容器
//		imgs.put("U", bulletImages[1]);
//		imgs.put("R", bulletImages[2]);
//		imgs.put("D", bulletImages[3]);
//	}


}
