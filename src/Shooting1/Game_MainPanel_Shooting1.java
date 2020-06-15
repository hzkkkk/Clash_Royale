package Shooting1;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;



public class Game_MainPanel_Shooting1 extends JPanel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 233L;
	/*---------------------------------配置--------------------------------*/
	// 面板常量
	  private static final int WIDTH = 880; 
	  private static final int HEIGHT = 660;
	 //线程常量
	  private Thread Thread_Paint_total;
	  private static final int Thread_Sleep=40;			//更新频率
	  
	  private Thread Thread_timecount;
	//状态变量
	  private boolean game_State= false; 
	//计时变量
	  private int time_count= 0; 
	 //双用户实例
	  Player1 player1= new Player1(this, 100, 200);
	  Player2  player2= new Player2(this);
	  //对象列表常量
		List<Bullet1> bullets1 = new ArrayList<Bullet1>();  
		List<Bullet2> bullets2 = new ArrayList<Bullet2>();  
		List<Explode> explodrations = new ArrayList<Explode>();
		List<Platform> platforms= new ArrayList<Platform>();
		
		protected int InfinityMode=0;
		//0为竞技模式，1为无尽模式
	  /*---------------------------------配置--------------------------------*/

	  
	  public Game_MainPanel_Shooting1()
	  {
		  bullets1.clear();
		//---------------------------初始化-------------------------//
		  // 设定初始构造时面板大小
		  setPreferredSize(new Dimension(WIDTH, HEIGHT));
		  //总线程Thread_Paint_total启动
		  Thread_Paint_total = new Thread(new Thread_Total(0));
		  Thread_Paint_total.start();
		  //总线程thread_timecount启动
		  Thread_timecount = new Thread(new Thread_Total(1));
		  Thread_timecount.start();
			
		  game_State=true;
		  this.platforms.add( new Platform( 150, 180, 180 ,40 , this));
		  this.platforms.add( new Platform( 350, 380, 180 ,40 , this));
		  this.platforms.add( new Platform( 550, 180, 180 ,40 , this));
//			List<Missile> missiles = new ArrayList<Missile>();  
//			List<Missile2> missile2s = new ArrayList<Missile2>();
//			List<Explode> explodrations = new ArrayList<Explode>();
	}
	
@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		set_MainPanel_Window_Menubar(g);
		/*---------------------------------绘制--------------------------------*/
										//----------角色1绘制----------//
		for (int j = 0; j < platforms.size(); j++) { 
			Platform plt = platforms.get(j);
			player1.ifCollideWithPlatform(plt);
		}
		//player1.ifCollideWithPlayer2(player2);
		player1.draw(g);
										//----------角色2绘制----------//
		for (int j = 0; j < platforms.size(); j++) { 
			Platform plt = platforms.get(j);
			player2.ifCollideWithPlatform(plt);
		}
		
		player2.draw(g);
		
										//----------子弹绘制----------//
		for (int i = 0; i < bullets1.size(); i++) 
		{ 
			Bullet1 bullet = bullets1.get(i);
			// 每一个子弹打平台上
			for (int j = 0; j < platforms.size(); j++) { 
				Platform plt = platforms.get(j);
				bullet.ifCollideWithPlatform(plt);
			}
			// 每一个子弹打角色2上
			bullet.ifCollideWithPlayer2(player2);
			bullet.draw(g); 
		}
		
		for (int i = 0; i < bullets2.size(); i++) 
		{ 
			Bullet2 bullet = bullets2.get(i);
			// 每一个子弹打平台上
			for (int j = 0; j < platforms.size(); j++) 
			{ 
				Platform plt = platforms.get(j);
				bullet.ifCollideWithPlatform(plt);
			}
			// 每一个子弹打角色2上
			bullet.ifCollideWithPlayer1(player1);
			bullet.draw(g); 
		}
		for (int i = 0; i < explodrations.size(); i++) 
		{ 
			Explode explode = explodrations.get(i); 
			explode.draw(g); 
		}
		for (int i = 0; i < platforms.size(); i++) 
		{ 
			Platform  pform= platforms.get(i); 
			pform.draw(g); 
		}
		/*---------------------------------绘制--------------------------------*/
	}
//UI设置
	public  void set_MainPanel_Window_Menubar(Graphics g)
	{
		Font f1 = g.getFont();
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.drawString("双人大战", 400, 70);
		if( InfinityMode ==0)
		{
			g.drawString("1P血量：", 50, 30);
			g.drawString("2P血量：", 750, 30);
			g.drawString(String.valueOf(player1.game_Life), 130, 30);
			g.drawString(String.valueOf(player2.game_Life), 830, 30);
		}
		else if( InfinityMode ==1)
		{
			g.drawString("1P被击中：", 50, 30);
			g.drawString("2P被击中：", 750, 30);
			g.drawString(String.valueOf(player1.Infinity_Count), 160, 30);
			g.drawString(String.valueOf(player2.Infinity_Count), 860, 30);
		}
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		if( game_State == false)
		{
			if(player2.game_Life <= 0)
				g.drawString("1P获胜", 380, 300);
			else if(player1.game_Life <= 0)
				g.drawString("2P获胜", 380, 300);
		}
		
		g.setFont(new Font("TimesRoman", Font.BOLD, 10));
		g.drawString("时间：", 400, 600);
		g.drawString(String.valueOf(time_count), 430, 600);
		
		g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
		g.setFont(f1);
	}
	
//总线程
	private class Thread_Total extends Thread implements Runnable 
	{
		Thread_Total(int temp){
			flag=temp;
		}

		private int flag;
		@Override
		public void run() 
		{
			while (game_State==true) 
			{
				try
				{
					if(player2.game_Life <= 0 || player1.game_Life <= 0)
					{
						if( InfinityMode ==0)
							game_State=false;
					}
					if(flag==0)  
					{
						Thread.sleep(Thread_Sleep);repaint();
					}
					else if(flag==1)  
					{
						Thread.sleep(1000);
						time_count++;
					}

				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}

			}
		}
	}

	
}
