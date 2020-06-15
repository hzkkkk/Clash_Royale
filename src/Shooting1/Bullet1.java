package Shooting1;


import java.awt.Graphics;


import javax.swing.ImageIcon;

import ShareConstant.Direction;

public class Bullet1 extends Bullets {
	 /*---------------------------------绘制--------------------------------*/
	public Bullet1(int x, int y, Direction dir, Game_MainPanel_Shooting1 client) {
		super(x, y, dir, client);
		ImageIcon icon = new ImageIcon(getClass().getResource("image/floor.gif"));
		BulletsImage = icon.getImage();
		if( BulletsImage == null ) 
			System.out.println("roleImage == null");
	}
	
		protected void draw (Graphics g){
			if(!(getLive()))
			{
				try {
					client.bullets1.remove(this);
				}catch(Exception e){
					e.printStackTrace();
				}
				return ;
			}
			g.drawImage( BulletsImage, x, y, null);
			move();
		}
		 /*---------------------------------绘制--------------------------------*/
		
	 /*--------------------------------检测--------------------------------*/
	public boolean ifCollideWithPlayer2 (Player2 player2){
		if(this.get_Rect().intersects(player2.get_Rect()) 
				&& player2.ifLive()==true )
		{
			this.ifLive = false; 
			if(dir.equals(Direction.L))
			{
				player2.x_speed=-40;
				player2.y_speed=-14;
			}
			if(dir.equals(Direction.R))
			{
				player2.x_speed=40;
				player2.y_speed=-14;
			}Explode e = new Explode(x, y, client); 
			//产生爆炸效果
			client.explodrations.add(e);
			
			//血量够，扣血
			player2.game_Life -= 1;
			player2.Infinity_Count++;
		}
//		else
//		{
//			//血量不够，死亡
//		player2.setLive(false);
		
		//Explode e = new Explode(x, y, client); //产生爆炸效果
		//client.explodrations.add(e);
		
		
//		return true;
//		}
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
//		imgs.put("L", bulletImages[0]); // 加入Map容器
//		imgs.put("U", bulletImages[1]);
//		imgs.put("R", bulletImages[2]);
//		imgs.put("D", bulletImages[3]);
//	}


	
}
