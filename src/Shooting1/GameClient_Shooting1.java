package Shooting1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


////////////////////////////////-----已完成-----/////////////////////////////////
public class GameClient_Shooting1 extends JFrame implements ActionListener 
{
	private static final long serialVersionUID = 11L;
	/*---------------------------------配置--------------------------------*/
	//窗口模块
	public static final int Fram_width = 800; // 静态全局窗口大小
	public static final int Fram_length = 600;
	
	//菜单模块
	MenuBar Super_Menu_Bar = null;
	Menu Super_Menu1 = null, Super_Menu2 = null, 
			Super_Menu3 = null, Super_Menu4 = null;
	MenuItem Super_Menu1_Option1 = null, Super_Menu1_Option2 = null, 
			Super_Menu2_Option1 = null, Super_Menu2_Option2 = null, 
			Super_Menu3_Option1 = null,Super_Menu3_Option2 = null,
			Super_Menu3_Option3 = null, Super_Menu3_Option4 = null, 
			Super_Menu4_Option1 = null;
	
	Image screen_Background = null;

	/*---------------------------------配置--------------------------------*/
	
	/*---------------------------------控制--------------------------------*/

	
	//Player1 player1= new Player1();// 实例化角色
	Game_MainPanel_Shooting1 mainPanel = new Game_MainPanel_Shooting1();
	/*---------------------------------控制--------------------------------*/
	
	/*---------------------------------游戏--------------------------------*/
	public static final int PLAYER1_GO=1;
	public static final int PLAYER2_GO=2;
	
	/*---------------------------------游戏--------------------------------*/
	
	
	public GameClient_Shooting1() 
	{
		//---------------------------初始化-------------------------//
		this.addKeyListener(new KeyMonitor());// 键盘监听
		setFocusable(true);
		set_Client_Window();		//窗口属性
		set_Client_Window_Menubar();		//菜单栏属性
		this.setMenuBar(Super_Menu_Bar);// 菜单Bar放到JFrame上
		
		Container contentPanel=this.getContentPane();
		contentPanel.add(mainPanel);
		pack();		//自适应窗体
			//List<Player1> Players = new ArrayList<Player1>();
			//List<Tank> tanks = new ArrayList<Tank>();
		}
	
	
		public  void set_Client_Window_Menubar()
		{
//新建菜单栏
			Super_Menu_Bar = new MenuBar();
		
//新建菜单和菜单项	
			Super_Menu1 = new Menu("开始");
			Super_Menu1_Option1 = new MenuItem("开始新游戏");
			Super_Menu1_Option2 = new MenuItem("退出");
			
			Super_Menu2 = new Menu("模式选择");
			Super_Menu2_Option1 = new MenuItem("竞技模式");
			Super_Menu2_Option2 = new MenuItem("无尽模式");
			
			Super_Menu3 = new Menu("血量选择");
			Super_Menu3_Option1 = new MenuItem("5血");
			Super_Menu3_Option2 = new MenuItem("7血");
			Super_Menu3_Option3 = new MenuItem("9血");
			Super_Menu3_Option4 = new MenuItem("11血");
			
			Super_Menu4 = new Menu("帮助");
			Super_Menu4_Option1 = new MenuItem("游戏说明");
			
			
//将菜单选项入菜单
			Super_Menu1.add(Super_Menu1_Option1);
			Super_Menu1.add(Super_Menu1_Option2);
			Super_Menu2.add(Super_Menu2_Option1);
			Super_Menu2.add(Super_Menu2_Option2);
			Super_Menu3.add(Super_Menu3_Option1);
			Super_Menu3.add(Super_Menu3_Option2);
			Super_Menu3.add(Super_Menu3_Option3);
			Super_Menu3.add(Super_Menu3_Option4);
			Super_Menu4.add(Super_Menu4_Option1);
			
			
//将菜单加入菜单栏
			Super_Menu_Bar.add(Super_Menu1);
			Super_Menu_Bar.add(Super_Menu2);
			Super_Menu_Bar.add(Super_Menu3);
			Super_Menu_Bar.add(Super_Menu4);
			
/******************信号****************/	
//为菜单选项添加监听器
			Super_Menu1_Option1.addActionListener(this);
			Super_Menu1_Option1.setActionCommand("NewGame");
			Super_Menu1_Option2.addActionListener(this);
			Super_Menu1_Option2.setActionCommand("Exit");
			Super_Menu2_Option1.addActionListener(this);
			Super_Menu2_Option1.setActionCommand("setRaceMode");
			Super_Menu2_Option2.addActionListener(this);
			Super_Menu2_Option2.setActionCommand("setInfinityMode");
			Super_Menu3_Option1.addActionListener(this);
			Super_Menu3_Option1.setActionCommand("Level1");
			Super_Menu3_Option3.addActionListener(this);
			Super_Menu3_Option3.setActionCommand("Level2");
			Super_Menu3_Option4.addActionListener(this);
			Super_Menu3_Option4.setActionCommand("Level3");
			Super_Menu3_Option4.addActionListener(this);
			Super_Menu3_Option4.setActionCommand("Level4");
			Super_Menu4_Option1.addActionListener(this);
			Super_Menu4_Option1.setActionCommand("Help");
/******************信号****************/		
			
// 设置菜单显示的字体
			Super_Menu1.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu2.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu3.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu4.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu1_Option1.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu1_Option2.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu2_Option1.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu2_Option2.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu3_Option1.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu3_Option2.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu3_Option3.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu3_Option4.setFont(new Font("微软雅黑", Font.BOLD, 15));
			Super_Menu4_Option1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		}
		
		
		public  void set_Client_Window()
		{
			// 设置窗口名称
			setTitle("双人大战1.0");
			//设置窗口大小并禁止缩放
			this.setSize(Fram_width, Fram_length);
			this.setResizable(false);
			// 设置窗口关闭操作
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// 显示窗口
			this.setVisible(true);
		}

		
		int which_hit_key(int KeyCode)
		{
			if(KeyCode==KeyEvent.VK_G ||
					KeyCode== KeyEvent.VK_H ||	
							KeyCode== KeyEvent.VK_W ||
									KeyCode == KeyEvent.VK_S ||
											KeyCode == KeyEvent.VK_A ||
													KeyCode== KeyEvent.VK_D ||
															KeyCode== KeyEvent.VK_R  )
				return PLAYER1_GO;
			else if(KeyCode==KeyEvent.VK_1 ||
							KeyCode== KeyEvent.VK_2 ||	
									KeyCode== KeyEvent.VK_UP ||
											KeyCode == KeyEvent.VK_DOWN ||
													KeyCode == KeyEvent.VK_LEFT ||
															KeyCode== KeyEvent.VK_RIGHT  )
				return PLAYER2_GO;
			else return -1;
		}
		
		
		//备用
				private class KeyMonitor extends KeyAdapter
				{
					public void keyReleased(KeyEvent e)
					{ // 监听键盘释放
						if( which_hit_key( e.getKeyCode() ) == PLAYER1_GO ) 
							mainPanel.player1.keyReleased(e);
						else if( which_hit_key( e.getKeyCode() ) == PLAYER2_GO ) 
							mainPanel.player2.keyReleased(e);
						}

					public void keyPressed(KeyEvent e)
					{ // 监听键盘按下
						if( which_hit_key( e.getKeyCode() ) == PLAYER1_GO )
							try {
								mainPanel.player1.keyPressed(e);
							} catch (NoSuchFieldException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						else if( which_hit_key( e.getKeyCode() ) == PLAYER2_GO ) 
							mainPanel.player2.keyPressed(e);
					}
				}
		
		

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("NewGame")) 
			{
				Object[] options = { "确定", "取消" };
				int result = JOptionPane.showOptionDialog(this, "开始新游戏吗？", "",
						JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						options, options[0]);
				if (result == 0) {
					this.dispose();
					new GameClient_Shooting1();
				} else {
					//printable
				}
			}
			if (e.getActionCommand().equals("Exit")) 
			{
				System.exit(0);
			}
			if (e.getActionCommand().equals("setRaceMode")) 
			{
				mainPanel.InfinityMode=0;
			}
			if (e.getActionCommand().equals("setInfinityMode")) 
			{
				mainPanel.InfinityMode=1;
			}
			if (e.getActionCommand().equals("Level1")) 
			{
				mainPanel.player1.game_Life=7;
				mainPanel.player2.game_Life=7;
			}
			if (e.getActionCommand().equals("Level2")) 
			{
				mainPanel.player1.game_Life=9;
				mainPanel.player2.game_Life=9;
			}
			if (e.getActionCommand().equals("Level3")) 
			{
				mainPanel.player1.game_Life=11;
				mainPanel.player2.game_Life=11;
			}
			if (e.getActionCommand().equals("Level4")) 
			{
				mainPanel.player1.game_Life=13;
				mainPanel.player2.game_Life=13;
			}
			if (e.getActionCommand().equals("Help")) 
			{
					JOptionPane.showMessageDialog(null, "双人大战游戏规则：\n"
							+ "玩家1 A，D左右移动 G攻击，H跳跃\n"
							+ "玩家2 ←，→左右移动 1攻击，2跳跃\n"
							, "游戏规则", JOptionPane.PLAIN_MESSAGE);
			}
			System.out.println("Receive:"+e.getActionCommand());	
	}
		
////////////////////////////////-----已完成-----/////////////////////////////////
}

