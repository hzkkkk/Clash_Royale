package Shooting1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import ShareConstant.Direction;

public abstract class Players extends JPanel  
{
	protected static final long serialVersionUID = 233L;
	/*---------------------------------配置--------------------------------*/
						         //---------地图边界常量----------//
	protected static final int x_limited_negative=-2;
	protected static final int x_limited_positive=860;
	protected static final int y_limitedGround=520;
									//---------贴图变量----------//
	protected Image roleImage;
	protected int Width = 32, Height = 32;
	protected int roleImage_width = 32, roleImage_height = 32; // 图片的全局大小，实时获取
	 								//---------基础变量----------//
	//角色移动x,y
	protected int x, y;
	protected int x_now, y_now;
	protected int x_speed, y_speed;
	//角色按键
	boolean U=false,D=false,L=false,R=false;
	//角色方向
	Direction dir;
	//角色血量
	protected int game_Life=0;
	 //线程常量
	  protected Thread thread_AllowFire;
	  
	protected Game_MainPanel_Shooting1 client;
	/*---------------------------------配置--------------------------------*/
	
	/*---------------------------------控制--------------------------------*/
								//---------角色移动常量----------//
	protected static final int MOBILIZABLE_LIMITED=6;

	protected static final double RESISTANCE_POWER=0.8;
	
	protected static final int SLOW_FAST_BOUNDARY=10;
	protected static final double RATE_of_SLOWSPEED_DECAY=0.6;
	protected static final double RATE_of_FASTSPEED_DECAY=0.85;
	protected static final double GRAVITY_ACCELERATION=1;
	protected static final double Y_SPEED_LIMITED=60;
	
								//---------角色状态变量----------//
	protected int  game_State=1;

	private  boolean ifLive =true;
	private boolean ifControl=false;	//可键盘控制的
	protected boolean if_player_with_ground=false;
	
	protected int Infinity_Count=0;
	
    /*---------------------------------控制--------------------------------*/

     /*---------------------------------游戏--------------------------------*/
		   						   //---------角色移动常量----------//
	protected static final int x_speed_power=10;		//跑动速度
	protected static final int y_speed_power=20;		//跳跃速度
	 								//---------子弹发射量----------//
	protected static final int Bt_Frency=400;
	
	protected boolean ifAllowFire=false;
     /*---------------------------------游戏--------------------------------*/
	
	 public Players()
	  {
		 
	  }

	public Players(Game_MainPanel_Shooting1 client, int x, int y)
	  {
		this.client=client;
			//---------------------------初始化-------------------------//
			this.x = 100;
			this.y = 300;
			//DEBUG
		    x_now=x;
			y_now=y;
			x_speed=20;
			y_speed=-20;
			//DEBUG
			game_Life=5;
			ifLive=true;
			ifControl=true;
			ifAllowFire=true;
			Width=roleImage_width;
			Height=roleImage_height;

			loadImage();
	  }
	public Players(Game_MainPanel_Shooting1 client)
	  {
		this.client=client;
			//---------------------------检测-------------------------//
			x = 0;
			y = 300;
			//DEBUG
		    x_now=1;
			y_now=200;
			x_speed=20;
			y_speed=-20;
			//DEBUG
			game_Life=5;
			ifLive=true;
			ifControl=true;
			ifAllowFire=true;
			Width=roleImage_width;
			Height=roleImage_height;

			loadImage();
	  }
	
	  public void draw(Graphics g)
	  {
			if (ifLive == false) 
			{
				return;
			}
			move();   
			g.drawImage( roleImage, x_now, y_now, this );
		}
	  
	  /*---------------------------------检测--------------------------------*/
	  public Rectangle get_Rect() 
		{
			return new Rectangle(x_now, y_now, roleImage_width, roleImage_height);
		}
	  		
		//是否低于坐标
		public boolean  get_ifCollideWithground()
		{
			return if_player_with_ground;
		}
		
		//是否低于坐标
		public void  set_ifCollideWithground(boolean b)
		{
			if_player_with_ground = b;
		}
	
		public boolean getifAllowFire() {
			return ifAllowFire;
		}

		public void setifAllowFire(boolean b) {
			ifAllowFire = b;
		}
		
		public boolean ifLive() {
			return ifLive;
		}

		public void setLive(boolean b) {
			ifLive = b;
		}
		
		public boolean ifControl() {
			return ifControl;
		}

		public void setifControl(boolean b) {
			ifControl = b;
		}
		
		
		abstract protected boolean ifCollideWithPlatform(Platform plt);
		/*---------------------------------检测--------------------------------*/

		
		/*---------------------------------绘制--------------------------------*/
		abstract protected void loadImage();
		/*--------------------------------绘制--------------------------------*/
		
		/*---------------------------------游戏--------------------------------*/
		//模拟重力和摩擦力
	
		public void move() 
		{		
			run();
			if( Math.abs(x_speed) <= SLOW_FAST_BOUNDARY )   x_speed *= RATE_of_SLOWSPEED_DECAY;
			else  x_speed *= RATE_of_FASTSPEED_DECAY;
		   if(y_now >= y_limitedGround)
			   set_ifCollideWithground(true);
			else 
				set_ifCollideWithground(false);
		    if( get_ifCollideWithground() == false )		//是否站在地上
		    {
		    	if( y_speed <= Y_SPEED_LIMITED)
		    		y_speed += GRAVITY_ACCELERATION;		//重力加速度
		    }
		    else
	    		y_speed=0;		
		}
		
		//响应方向键
				public void run() 
				{
					if( ifControl())
					{
							if( R && U )		//按下右方向键 
							{
								if(Math.abs(x_speed)>MOBILIZABLE_LIMITED)    	x_speed +=RESISTANCE_POWER;
								else   x_speed += (x_speed_power - x_speed);
								
								if( if_player_with_ground == true)
							    {
									y_speed = -y_speed_power;			//跳
							    }
							}
							else if( L && U )		//按下左方向键
							{
								if(Math.abs(x_speed)>MOBILIZABLE_LIMITED)    x_speed -= RESISTANCE_POWER;
								else  x_speed += (-x_speed_power - x_speed );
								if( if_player_with_ground == true)
							    {
									y_speed = -y_speed_power;			//跳
							    }
							}
							else if( L )		//按下左方向键 
							{
								if(Math.abs(x_speed)>MOBILIZABLE_LIMITED)  x_speed += RESISTANCE_POWER;
								else   x_speed+= ( -x_speed_power - x_speed);
							}
							else if( R )		//按下右方向键
							{
								if(Math.abs(x_speed)>MOBILIZABLE_LIMITED)    x_speed  -= RESISTANCE_POWER;
								else  x_speed += (x_speed_power - x_speed);
							}
							else if( U )
							{
								if( if_player_with_ground == true )
							    {
									y_speed = -y_speed_power;			//跳
							    }
							}			

							if( x_now <= x_limited_negative)
							{
								if( x_speed < 0)
									x_speed = 0;
							}
							else if( x_now >= x_limited_positive)
							{
								if( x_speed > 0)
									x_speed = 0;
							}
					}
					x_now += x_speed;
					y_now += y_speed;
				}	
		
				//计算线程
				class Thread_AllowFire extends Thread implements Runnable 
				{
					@Override
					public void run() 
					{
							try 
							{
								setifAllowFire(false);
								Thread.sleep(Bt_Frency);
								setifAllowFire(true);
							}
							catch (InterruptedException e)
							{
								e.printStackTrace();
							}
					}
				}
				
				
		/*---------------------------------游戏--------------------------------*/
		
		abstract protected void keyTyped( KeyEvent e);
		abstract protected void keyPressed( KeyEvent e) throws NoSuchFieldException ;
		abstract protected void keyReleased( KeyEvent e) ;
		
}
