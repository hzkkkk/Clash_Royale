package Shooting1;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import ShareConstant.Direction;
import Test_Annotation.FunctionAnnotation;
import Test_Annotation.ParseAnnotation;;

@FunctionAnnotation()
public class Player1 extends Players // implements KeyListener_Moving 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 233L;

	/*---------------------------------配置--------------------------------*/
	@Override
 	protected void loadImage()
	{
		ImageIcon icon = new ImageIcon(getClass().getResource("image/hero.gif"));
		System.out.println(getClass().getResource("image/hero.gif"));
		roleImage = icon.getImage();
		////g.drawImage(roleImage, x * CS, y * CS, this);
		if( roleImage == null ) 
			System.out.println("roleImage == null");//// count*CS   CS+count*CS
	}														//// x+CS ,y+CS, 0, 0, CS, CS, 
	
	/*---------------------------------配置--------------------------------*/
	
	
	  public Player1(Game_MainPanel_Shooting1 client)
	  {
		  super(client);
	  }
	  public Player1(Game_MainPanel_Shooting1 client, int x, int y)
	  {
		  super(client,  x,  y);
	  }

	   /*--------------------------------检测--------------------------------*/
	  
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
	  
//是否与角色相撞
	  public boolean ifCollideWithPlayer2(Player2 player2) 
		  { 
				if ( this.ifLive()==true && this.get_Rect().intersects(player2.get_Rect()) ) 
				{
					set_ifCollideWithground(true);
					setifControl(true);
					y_now -= y_speed;
					y_speed = 0;
					if ( this.ifLive()==true && this.get_Rect().intersects(player2.get_Rect()) ) 
					{
//						x_now -= x_speed/0.8;
//						x_speed = 0;
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
	  /*--------------------------------检测--------------------------------*/
	  
	  
/*---------------------------------射击--------------------------------*/
	  @FunctionAnnotation(sender = "Player1",
				receiver = "Bullet1",function = "fire()")
		public Bullet1 fire()
		{
			if(ifLive() == false || getifAllowFire() == false)
				return null;
			thread_AllowFire = new Thread(new Thread_AllowFire());
			thread_AllowFire.start();
			int x = this.x_now+this.roleImage_width/2 - Bullet1.Width/2;
			int y = this.y_now+this.roleImage_height/2 - Bullet1.Height/2;
			Bullet1 b1 = null;
//
			b1 = new Bullet1(x, y, dir, client);
//
			client.bullets1.add(b1);
			return b1;
		}
	  
/*---------------------------------射击--------------------------------*/
	  	
	  /*------------------------------键盘操控--------------------------------*/
		@Override
		protected void keyTyped( KeyEvent e)
		{
			
		}
		
		protected void keyPressed( KeyEvent e) throws NoSuchFieldException 
		{
			try {
				switch (e.getKeyCode())
				{
					 case KeyEvent.VK_G:
						 ParseAnnotation.Parse();
						 //fire();
					
					break;
				case KeyEvent.VK_A:
					L=true;
					dir=Direction.L;
					break;
				case KeyEvent.VK_D:
					R=true;
					dir=Direction.R;
					break;
				case KeyEvent.VK_H:
					U=true; 
					break;
				case KeyEvent.VK_S:
					D=true;
					break;
					}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
 
		protected void keyReleased( KeyEvent e)
		{
			switch (e.getKeyCode()) 
			{
			case KeyEvent.VK_A:
				L=false;
				break;
			case KeyEvent.VK_D:
				R=false;
				break;
			case KeyEvent.VK_H:
				U=false;
				break;
			case KeyEvent.VK_S:
				D=false;
				break;
			}
		}	
		  /*------------------------------键盘操控--------------------------------*/

	
}







