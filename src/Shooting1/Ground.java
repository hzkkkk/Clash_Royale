package Shooting1;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

	public class Ground {
		private int x, y;
		private int width, height;
		
		private static Toolkit tk = Toolkit.getDefaultToolkit();
		
		@SuppressWarnings("unused")
		private static Image[] wallImags = null;
		
		static {
			wallImags = new Image[] { tk.getImage(Ground.class
					.getResource("Images/metalwall.gif")), };
		}
		
		public Rectangle getRect() 
		{
			return new Rectangle(x, y, width, height);
		}

}
	
