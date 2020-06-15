package Shooting1;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import ShareConstant.Direction;

public abstract  class Bullets {
	/*---------------------------------配置--------------------------------*/
								//---------贴图变量----------//
	public static final int Width = 10;
	public static final int Height = 10;
	//protected static Toolkit tKit = Toolkit.getDefaultToolkit();
	protected Image BulletsImage;
								//---------基础变量----------//
	protected int x, y;
	Direction dir;  //移动方向
	protected Game_MainPanel_Shooting1 client;
	/*---------------------------------����--------------------------------*/


	/*---------------------------------控制--------------------------------*/
								//---------子弹状态变量----------//
	protected boolean ifLive=true;		//是否存在

/*---------------------------------控制--------------------------------*/

/*---------------------------------游戏--------------------------------*/
								//---------子弹飞行常量----------//
	protected static final int x_speed=10;		//跑动速度
	protected static final int y_speed=20;		//跳跃速度
/*---------------------------------游戏--------------------------------*/
	
	protected Bullets(int x, int y, Direction dir, Game_MainPanel_Shooting1 client) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.client=client;
	}
	
	protected boolean getLive() {
		return ifLive;
	}
	protected void setLive(boolean Live) {
		this.ifLive=Live;
	}
	
	abstract protected void draw (Graphics g);

	protected Rectangle get_Rect (){
		return new Rectangle(x,y,Width,Height);
	}
	

	public void ifCollideWithPlatform(Platform plt) { 
		if (this.getLive() && this.get_Rect().intersects(plt.get_Rect())) {
			this.setLive(false);
		}
	}
	
	protected void move() {

		switch (dir) 
		{
		case L:
			x -= x_speed; 
			break;

//		case U:
//			y -= y_speed;
//			break;

		case R:
			x += x_speed; 
			break;
		default:
			break;

//		case D:
//			y += y_speed;
//			break;
		}
		
		if ( x > GameClient_Shooting1.Fram_width
				|| y > GameClient_Shooting1.Fram_length) {
			ifLive = false;
		}
	}
	
}
