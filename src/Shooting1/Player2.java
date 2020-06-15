package Shooting1;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import ShareConstant.Direction;

public class Player2 extends Players   //  implements KeyListener 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 233L;

	/*---------------------------------配置--------------------------------*/
	@Override
 	protected void loadImage()
	{
		ImageIcon icon = new ImageIcon(getClass().getResource("image/hero.jpg"));
		System.out.println(getClass().getResource("image/hero.jpg"));
		roleImage = icon.getImage();
		////g.drawImage(roleImage, x * CS, y * CS, this);
		if( roleImage == null ) 
			System.out.println("roleImage == null");//// count*CS   CS+count*CS
	}									//// x+CS ,y+CS, 0, 0, CS, CS, 
	//////g.drawImage(roleImage, x*roleImage.getWidth(this), y*roleImage.getHeight(this),this);

	/*---------------------------------配置--------------------------------*/
	
	  public Player2(Game_MainPanel_Shooting1 client)
	  {
		  super(client);
	  }
	  public Player2(Game_MainPanel_Shooting1 client, int x, int y)
	  {
		  super(client,  x,  y);
	  }
/*---------------------------------检测--------------------------------*/

	  @Override
	//是否与平台相撞，在四个方位相撞
			public boolean ifCollideWithPlatform(Platform plt) 
		  { 
				if ( this.ifLive()==true && this.get_Rect().intersects(plt.get_Rect()) ) 
				{
					set_ifCollideWithground(true);
					setifControl(true);
					y_now -= y_speed;
					y_speed = 0;
					if ( this.ifLive()==true && this.get_Rect().intersects(plt.get_Rect()) ) 
					{
						x_now -= x_speed/0.8;
						x_speed = 0;
						
						if ( this.ifLive()==true && this.get_Rect().intersects(plt.get_Rect()) ) 
							setifControl(false);
					}
//					
//					if(y_now > plt.y)
//					{
//						System.out.println("y_speed=0");
//						y_now -= y_speed;
//						y_speed = 0;
//					}
//					else
//					{
//						System.out.println("y_speed=0");
//						y_now -= y_speed;
//						y_speed = 0;
//					}
//					if(Math.abs(y_now - plt.y) < (plt.get_Height()+ Height)/2 )
//					{
//						System.out.println("x_speed=0");
//						x_now -= x_speed;
//						x_speed = 0;
//					}
					return true;
				}
				return false;
			}
		  

/*---------------------------------����--------------------------------*/
		
	//是否与角色相撞
		public boolean ifCollideWithPlayer1(Player1 player1) 
	  { 
			if ( this.ifLive()==true && this.get_Rect().intersects(player1.get_Rect()) ) 
			{
				set_ifCollideWithground(true);
				setifControl(true);
				y_now -= y_speed;
				y_speed = 0;
				if ( this.ifLive()==true && this.get_Rect().intersects(player1.get_Rect()) ) 
				{
//					x_now -= x_speed/0.8;
//					x_speed = 0;
					if( dir == Direction.L)
					{
						x_now += 6;
						x_speed = 0;
					}
					else	if( dir == Direction.R) {
						x_now -= 6;
						x_speed = 0;
					}
				}
//				
//				if(y_now > plt.y)
//				{
//					System.out.println("y_speed=0");
//					y_now -= y_speed;
//					y_speed = 0;
//				}
//				else
//				{
//					System.out.println("y_speed=0");
//					y_now -= y_speed;
//					y_speed = 0;
//				}
//				if(Math.abs(y_now - plt.y) < (plt.get_Height()+ Height)/2 )
//				{
//					System.out.println("x_speed=0");
//					x_now -= x_speed;
//					x_speed = 0;
//				}
				return true;
			}
			return false;
		}
/*--------------------------------检测--------------------------------*/



/*---------------------------------射击--------------------------------*/
		public Bullet2 fire ()
		{
			if(ifLive() == false || getifAllowFire() == false)
				return null;
			thread_AllowFire = new Thread(new Thread_AllowFire());
			thread_AllowFire.start();
			int x = this.x_now+this.roleImage_width/2 - Bullet2.Width/2;
			int y = this.y_now+this.roleImage_height/2 - Bullet2.Height/2;
			Bullet2 b2 = null;
			b2 = new Bullet2(x, y, dir, client);
			client.bullets2.add(b2);
			return b2;
		}
	  
	  	/*---------------------------------射击--------------------------------*/

	  /*------------------------------键盘操控--------------------------------*/
		@Override
		protected void keyTyped( KeyEvent e)
		{}
		
		  @Override
		protected void keyPressed( KeyEvent e) 
		{
			switch (e.getKeyCode())
			{
			case KeyEvent.VK_1:
				fire();
				break;
			case KeyEvent.VK_LEFT:
				L=true;
				dir=Direction.L;
				break;
			case KeyEvent.VK_RIGHT:
				R=true;
				dir=Direction.R;
				break;
			case KeyEvent.VK_2:
				U=true; 
				break;
			case KeyEvent.VK_DOWN:
				D=true;
				break;
				}
		}
 
		protected void keyReleased( KeyEvent e)
		{
			switch (e.getKeyCode()) 
			{
			case KeyEvent.VK_LEFT:
				L=false;
				break;
			case KeyEvent.VK_RIGHT:
				R=false;
				break;
			case KeyEvent.VK_2:
				U=false;
				break;
			case KeyEvent.VK_DOWN:
				D=false;
				break;
			}
		}	
		  /*------------------------------键盘操控--------------------------------*/


		
}







