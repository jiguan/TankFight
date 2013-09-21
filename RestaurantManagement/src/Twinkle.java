import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.JPanel;
import javax.swing.JWindow;

class Twinkle extends JPanel implements Runnable
{

	Thread thread;
	int x = 10;
	int i=0, j=40, k=0, u=10, t=0;
	String status[]={"The","system","is","loading"};
	String title[]={"Welcome","you"};
	Font font = new Font("Arial", Font.PLAIN,18);
	
	boolean flag = true;
	int width = 180;
	int height = 0;
	int dian=0;
	
	Twinkle()
	{
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			if(x<380) repaint();
			try {
				Thread.sleep(70);
				i++;
				j=j-6;
				u=u+10;
				if(t==3) width = width-20;
				t++;
				if(flag&&x>120+k*20) k++;
				if(k>=status.length-1) flag=false;
				x=x+10;
				i=0;
				j=40;
				u=10;
				dian++;
				if(dian>3) dian=0;
			} catch (InterruptedException e)
			{
				System.out.println("Thread stops");
			}
			
		}
	}
	
	public void paintComponent (Graphics g)
	{
		//super.paintComponent(g);
		String image_path = "/home/jiguan/workspace/RestaurantManagement/lib/background.jpg";
		Image image = Toolkit.getDefaultToolkit().getImage(image_path);
		g.drawImage(image,0,0,this.getWidth(),200,this);
		int r = (int) (Math.random()*255);
		int b = (int) (Math.random()*255);
		int y = (int) (Math.random()*255);
		
		g.setColor(new Color(253,250,250));
		g.fillRect(x, 210, 390-x, 30);
		g.setColor(new Color(253,250,250));
		if(i>1) g.fillRect(x, 225-(j+20)/2, 10, j+20);
		if(j>25) g.setColor(new Color(r,b,y));
		else g.setColor(new Color(123,194,252));
		g.fillRect(x,225-j/2, 10, j);
		g.setColor(new Color(123,194,252));
		g.drawRect(10,210,380,30);
	
		if (x<120)
		{
			for(int l=0;l<status.length;l++)
			{
				g.setColor(new Color(0,0,0));
				g.drawString(status[l],120+l*20,230);
			}
			for(int l=0;l<dian;l++)
			{
				g.setColor(new Color(0,0,0));
				g.drawString("*",300+l*10,235);
			}
			g.drawString("*",300+10*dian,235);
		}
		else
		{
			g.setColor(new Color(23,23,230));
			g.drawString(status[k],120+k*20,230);
			for(int l=k+1;l<status.length;l++)
			{
				g.setColor(new Color(0,0,0));
				g.drawString(status[l],120+l*20,230);
			}
			if(x>300+dian*10) g.setColor(new Color(23,23,230));
			for(int l=0;l<dian;l++)
			{
				g.drawString("*", 300+l*10, 235);
			}
			g.drawString("*",300+10*dian,235);
		}
		
		if(t<3)
		{
			for(int rr=0;rr<t;rr++)
			{
				g.setColor(new Color(r,b,y));
				g.drawString(title[rr], 180, 60+rr*20);
			}
			g.drawString(title[t], 180, 60+t*20);
		}
		if(t>=3&&t<7)
		{
			g.setColor(new Color(230,0,0));
			for(int rr=0;rr<2;rr++)
				g.drawString(title[rr], 180, 60+rr*20);
			g.setColor(new Color(r,b,y));
			
			if(t>=7&&t<13)
			{
				g.setColor(new Color(230,0,0));
				for(int rr=0;rr<3;rr++)
					g.drawString(title[rr], 180, 60+rr*20);
				for(int rr=3;rr<=7;rr++)
					g.drawString(title[rr], 150, rr*20-20);
				g.setColor(new Color(r,b,y));
				if(t<13)
					for(int rr=8;rr<=t;rr++)
						g.drawString(title[rr], 120, rr*20);
				if(t>=13)
					for(int rr=8;rr<13;rr++)
						g.drawString(title[rr], 120, rr*20);
			}
		}
	}
}
