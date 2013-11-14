import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TankGame extends JFrame implements ActionListener 
{	
	static int width, height;
	static private int score = 0;
	static int hero_x, hero_y;
	Panel my_panel;
	StartPanel start_panel;
	JTextArea record_panel;
	//StatusPanel status_panel;
	JMenuBar menu_bar;
	JMenu menu1, menu2;
	JMenuItem menu_item11, menu_item12, menu_item13, menu_item14, menu_item21 , menu_item22;
	JButton jb1;
	Thread t;
	public static void main (String [] args)
	{
		TankGame game = new TankGame();
	}
	
	public TankGame()
	{
		menu_bar = new JMenuBar();
		menu1 = new JMenu("Game (G)");
		menu1.setMnemonic('G');
		menu2 = new JMenu("Setting (S)");
		menu2.setMnemonic('S');
		//menu1
		menu_item11 = new JMenuItem("Start new Game");
		menu_item12 = new JMenuItem("Load");
		menu_item13 = new JMenuItem("Save");
		menu_item14 = new JMenuItem("Exit");
		//add items
		menu1.add(menu_item11);
		menu1.add(menu_item12);
		menu1.add(menu_item13);
		menu1.add(menu_item14);
		menu_bar.add(menu1);
		//menu2
		menu_item21 = new JMenuItem("Show scores");
		menu_item22 = new JMenuItem("Game setting");
		menu2.add(menu_item21);
		menu2.add(menu_item22);
		menu_bar.add(menu2);
		//register
		menu_item11.addActionListener(this);
		menu_item11.setActionCommand("Start");
		menu_item12.addActionListener(this);
		menu_item12.setActionCommand("Load");
		menu_item13.addActionListener(this);
		menu_item13.setActionCommand("Save");
		menu_item14.addActionListener(this);
		menu_item14.setActionCommand("Exit");
		
		menu_item21.addActionListener(this);
		menu_item21.setActionCommand("Show record");
		menu_item22.addActionListener(this);
		menu_item22.setActionCommand("Settting");
		
		//Initial 
		width = 500;
		height = 400;
		hero_x = width/3;
		hero_y = height-100;
		
		start_panel = new StartPanel();
		this.t = new Thread(start_panel);
		this.t.start();
		this.setJMenuBar(menu_bar);
		this.add(start_panel);
		this.setSize(width+100,height);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Start"))
		{
			t.interrupt();			
			try {
				if (t.getName().equals("Thread-1")) {
					my_panel = new Panel();
					this.t = new Thread(my_panel);
					this.t.start();
					//delete old panel
					this.remove(start_panel);
					this.add(my_panel);
					this.setSize(width+100, height+100);
					//this.add(status_panel, BorderLayout.SOUTH);
					this.addKeyListener(my_panel);
					this.setVisible(true);
				} else {
					this.remove(my_panel);
					my_panel = new Panel();
					this.t = new Thread(my_panel);
					this.t.start();
					//delete old panel
					this.remove(start_panel);
					this.add(my_panel);
					this.setSize(width+100, height+100);
					//this.add(status_panel, BorderLayout.SOUTH);
					this.addKeyListener(my_panel);
					this.setVisible(true);
				}
			
			} catch (Exception e3) {
				e3.printStackTrace();
			}
			
			
			
			
		}
		else if(e.getActionCommand().equals("Load"))
		{	

			Panel.new_game = false;
			Vector result[] = Record.readRecord();
			System.out.println("result[0]:"+result[0].size());
			my_panel = new Panel(result[0],result[1]);
			//my_panel.nodes = ;
			TankGame.score = Record.getScore();
			//Thread t = new Thread(my_panel);
			this.t = new Thread(my_panel);
			this.t.start();
			//delete old panel
			this.remove(start_panel);
			this.add(my_panel);
			this.setSize(width+100, height);
			//this.add(status_panel, BorderLayout.SOUTH);
			this.addKeyListener(my_panel);
			this.setVisible(true);
		}
		
		else if(e.getActionCommand().equals("Save"))
		{	
			Vector <Enemy> ets = new Vector<Enemy>();
			Vector <Enemy> ats = new Vector<Enemy>();
			ets = Panel.ets;
			ats = Panel.ats;
			Record.setEts(ets);
			Record.setAts(ats);
			Record.saveRecord();
		}
		else if(e.getActionCommand().equals("Exit"))
		{
			System.exit(0);
		}
		else if(e.getActionCommand().equals("Show record"))
		{
			int score = Record.readScore();
			jb1 = new JButton("Continue");
			record_panel = new JTextArea(Integer.toString(score));
			this.add(jb1,BorderLayout.SOUTH);
		}
		else if(e.getActionCommand().equals("Setting"))
		{
			
			
		}
	}
	
	{
		this.height = height;
	}
	public int getWdith()
	{
		return this.width;
	}
	public int getHeight()
	{
		return this.height;
	}
	public static int getScore()
	{
		return TankGame.score;				
	}
	public static void addScore()
	{
		TankGame.score++;
	}
	

}

class StartPanel extends JPanel implements Runnable
{
	int time = 0;
	public void paint (Graphics g)
	{
		super.paint(g);
		g.fillRect(0, 0, TankGame.width+100, TankGame.height);
		if(time%2==0) 
		{
			g.setColor(Color.yellow);
			Font title_font = new Font ("Arial Balck",Font.BOLD,50);
			g.setFont(title_font);
			g.drawString("Tank Fight",TankGame.width/3,TankGame.height/3);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				System.out.println("Interrupte startpanel");
			} catch (Exception e) {
				e.printStackTrace();
			}
			time = (time+1)%2;
			this.repaint();
		}
	}
}


//Game Panel
class Panel extends JPanel implements KeyListener, Runnable
{
	//define my tank
	Hero hero;
	static Vector<Enemy> ets;
	static Vector<Enemy> ats;
	Vector<Explosion> bombs;
	static Vector<Node> nodes;
	int enemy_num;
	int ally_num;
	Image image1, image2, image3;
	boolean init = false;
	static boolean new_game = true;
	Thread tank_thread;
	
	public Panel()
	{	
		int hero_x = TankGame.width/3;
		int hero_y = TankGame.height-100;
		ets = new Vector<Enemy>();
		ats = new Vector<Enemy>();
		bombs = new Vector<Explosion>();
		nodes = new Vector<Node>();
		hero = new Hero (hero_x,hero_y);
		
		enemy_num = 7;
		ally_num = 7;
		for(int i=0;i<enemy_num;i++)
		{
			Enemy enemy_tank = new Enemy((i+1)*50, 20);
			ets.add(enemy_tank);
			enemy_tank.setDirect(2);
			//give other enemy's vector to this new tank
			enemy_tank.setEts(ets);
			//start enemy tank
			tank_thread = new Thread(enemy_tank);
			tank_thread.start();
		}
		//Initial ally
		for(int i=0;i<ally_num;i++)
		{
				Enemy ally_tank = new Enemy((i+1)*50+20,TankGame.height-100);
				ats.add(ally_tank);
				ally_tank.setDirect(0);
				//avoid overlap
				ally_tank.setEts(ats);
				//start allies tank
				tank_thread = new Thread(ally_tank);
				tank_thread.start();
		}
		//Initial image
		try {
			image1 = ImageIO.read(new File("src/explosive2.png"));
			image2 = ImageIO.read(new File("src/explosive2.png"));
			image3 = ImageIO.read(new File("src/explosive3.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosive2.png"));
//		image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosive2.png"));
//		image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosive3.png"));

	}
	
	public Panel(Vector<Node> enemy_nodes, Vector<Node> ally_nodes) {
		hero = new Hero (TankGame.hero_x,TankGame.hero_y);
		Thread tank_thread;
		if(enemy_nodes != null) {
			for(int i=0;i<enemy_nodes.size();i++)
			{
				Node node = enemy_nodes.get(i);
				Enemy enemy_tank = new Enemy(node.x, node.y);
				ets.add(enemy_tank);
				enemy_tank.setDirect(node.direct);
				//give other enemy's vector to this new tank
				enemy_tank.setEts(ets);
				//start enemy tank
				tank_thread = new Thread(enemy_tank);
				tank_thread.start();
			}
		}
		//recall ally
		if (ally_nodes != null)
		{
			for(int i=0;i<ally_nodes.size();i++)
			{
				Node node = ally_nodes.get(i);
				Enemy ally_tank = new Enemy(node.x,node.y);
				ats.add(ally_tank);
				ally_tank.setDirect(node.direct);
				//avoid overlap
				ally_tank.setEts(ats);
				//start allies tank
				tank_thread = new Thread(ally_tank);
				tank_thread.start();
			}
		}
		//Initial image
		try {
			image1 = ImageIO.read(new File("src/explosive1.png"));
			image2 = ImageIO.read(new File("src/explosive2.png"));
			image3 = ImageIO.read(new File("src/explosive3.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosive2.png"));
//		image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosive2.png"));
//		image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/explosive3.png"));

	}
		
	//repaint
	public void paint(Graphics g) 
	{
		super.paint(g);
		//set background
		g.fillRect(0,0,TankGame.width,TankGame.height);
		//draw status area
		g.setColor(Color.DARK_GRAY);
		g.fillRect(TankGame.width,0,TankGame.width+100,TankGame.height);
		showStatus(hero.life,ets.size(),ats.size(),g);

		//draw my tank
		if(hero.isLive)
			this.drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);
		else 
		{
			if (hero.life>0)
			{
				hero.isLive = true;
			}
		}
		//draw allies tanks
				for(int i=0;i<ats.size();i++)
				{
					Enemy ally_tank = ats.get(i);
					if(ally_tank.isLive)
						this.drawTank(ally_tank.getX(),ally_tank.getY(),g,ally_tank.getDirect(),2);
					//draw allies's bullets
					g.setColor(Color.LIGHT_GRAY);
					for(int j=0;j<ally_tank.bullets.size();j++)
					{
						Bullet ally_bullet = ally_tank.bullets.get(j);
						if(ally_bullet.isLive)
							g.draw3DRect(ally_bullet.x, ally_bullet.y,1,1,false);
						else
							ally_tank.bullets.remove(ally_bullet);
					}
				}
		
		
		//draw bullet
		for(int i=0;i<this.hero.bullets.size();i++)
		{
			Bullet one_bullet = hero.bullets.get(i);
			if(one_bullet.isLive)
				g.draw3DRect(one_bullet.x, one_bullet.y, 1, 1,false);
			else
				hero.bullets.remove(one_bullet);
		}
		//draw bombs
		for(int i=0;i<bombs.size();i++)
		{
			Explosion bomb = bombs.get(i);
			if(bomb.life>6) 
			{
				g.drawImage(image1, bomb.x, bomb.y, 30, 30, this);
				//System.out.println("Image1");
			}
			else if(bomb.life>3) {
				g.drawImage(image2, bomb.x, bomb.y, 30, 30, this);
				//System.out.println("Image2");
			}
			else {
				g.drawImage(image3, bomb.x, bomb.y, 30, 30, this);
				//System.out.println("Image3");
			}
			bomb.lapse();
			if(bomb.isLive==false)
				bombs.remove(bomb);
		}
		
		//draw enemy tanks
		for(int i=0;i<ets.size();i++)
		{
			Enemy enemy_tank = ets.get(i);
			if(enemy_tank.isLive) 
				this.drawTank(enemy_tank.getX(),enemy_tank.getY(),g,enemy_tank.getDirect(),1);
			//draw enemy's bullets
			g.setColor(Color.yellow);
			for(int j=0;j<enemy_tank.bullets.size();j++)
			{
				Bullet enemy_bullet = enemy_tank.bullets.get(j);
				if(enemy_bullet.isLive)
					g.draw3DRect(enemy_bullet.x, enemy_bullet.y,1,1,false);
				else
					enemy_tank.bullets.remove(enemy_bullet);
			}
		}
	}
	
	public void showStatus(int life, int ets_size, int ats_size, Graphics g)
	{
		int x = TankGame.width + 20;
		int y = TankGame.height;
		this.drawTank(x, 40, g, 0, 0);
		g.drawString(Integer.toString(life), x+20, 40);
		this.drawTank(x, 90, g, 0, 1);
		g.drawString(Integer.toString(ets_size), x+20, 90);
		this.drawTank(x, 140, g, 0, 2);
		g.drawString(Integer.toString(ats_size), x+20, 140);
		
		//show score
		Font score_font = new Font ("Arial Balck",Font.BOLD,15);
		g.setFont(score_font);
		int score = TankGame.getScore();
		g.drawString("Score: "+score,x-10,190);
	}
	
	public boolean hitTank(Bullet bullet, Tank enemy_tank)
	{	
		boolean isHit = false;
		if (enemy_tank.isLive)
		{
			switch(enemy_tank.direct)
			{
			case 0:
			case 2:
				if(bullet.x>(enemy_tank.x-10)&&bullet.x<(enemy_tank.x+10)&&bullet.y<(enemy_tank.y+15)&&bullet.y>(enemy_tank.y-15))
				{
					bullet.isLive = false;
					enemy_tank.isLive = false;
					Explosion bomb = new Explosion(enemy_tank.x-10,enemy_tank.y-15);
					bombs.add(bomb);
					isHit = true;
				}
				break;
			case 1:
			case 3:
				if(bullet.x>(enemy_tank.x-15)&&bullet.x<(enemy_tank.x+15)&&bullet.y<(enemy_tank.y+10)&&bullet.y>(enemy_tank.y-10))
				{
					bullet.isLive = false;
					enemy_tank.isLive = false;
					Explosion bomb = new Explosion(enemy_tank.x-10,enemy_tank.y-15);
					bombs.add(bomb);
					isHit = true;
				}
				break;
			}
		}
		return isHit;
	}
	
	//draw tank
	public void drawTank(int x, int y, Graphics g, int direction, int type)
	{
		switch (type)
		{
			case 0:
				g.setColor(Color.white);
				break;
			case 1:
				g.setColor(Color.yellow);
				break;
			case 2:
				g.setColor(Color.LIGHT_GRAY);
		}
		switch (direction)
		{
			case 0:
				g.fill3DRect(x-10,y-15,5,30,false);
				g.fill3DRect(x+5,y-15,5,30,false);
				g.fill3DRect(x-5,y-10,10,20,false);
	            g.fillOval(x-5,y-5,10,10);
				g.drawLine(x,y-15,x,y);
				break;
			case 1:
				g.fill3DRect(x-15,y-10,30,5,false);
				g.fill3DRect(x-15,y+5,30,5,false);
				g.fill3DRect(x-10,y-5,20,10,false);
	            g.fillOval(x-5,y-5,10,10);
				g.drawLine(x,y,x+15,y);
				break;
			case 2:
				g.fill3DRect(x-10,y-15,5,30,false);
				g.fill3DRect(x+5,y-15,5,30,false);
				g.fill3DRect(x-5,y-10,10,20,false);
	            g.fillOval(x-5,y-5,10,10);
				g.drawLine(x,y+15,x,y);
				break;
			case 3:
				g.fill3DRect(x-15,y-10,30,5,false);
				g.fill3DRect(x-15,y+5,30,5,false);
				g.fill3DRect(x-10,y-5,20,10,false);
	            g.fillOval(x-5,y-5,10,10);
				g.drawLine(x,y,x-15,y);
				break;	
		}
	}
	public void keyPressed(KeyEvent e) 
	{
		if (hero.isLive==false);
		//my tank
		else if(e.getKeyCode()==KeyEvent.VK_W)
		{	
			this.hero.moveUp();
		}
		else if (e.getKeyCode()==KeyEvent.VK_D)
		{
			this.hero.turnRight();
		}
		else if (e.getKeyCode()==KeyEvent.VK_S)
        {
			this.hero.stepBack();
        }
		else if (e.getKeyCode()==KeyEvent.VK_A)
        {
			this.hero.turnLeft();
        } 
		if (e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if (this.hero.bullets.size()<this.hero.max_bullet)
			{
				this.hero.fire();
				//System.out.print("Remain bullets: "+(this.hero.max_bullet-this.hero.bullets.size()));
			}
		}
		this.repaint();
	}
	public void keyReleased(KeyEvent e)
	{
	}
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String explosion_file = "src/Bomb_Explosion.wav";
		SoundPlay sound_play = new SoundPlay(explosion_file);
		
		while(true)
		{
			try{
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Interrupte Panel");
				return;
			} catch (Exception e) {
				e.printStackTrace();
			
			}
			//check whether enemy is hit by hero
			for(int i=0;i<hero.bullets.size();i++)
			{
				Bullet one_bullet = hero.bullets.get(i);
				//check bullet is valid
				if(one_bullet.isLive)
				{
					//match with every enemy tank
					for(int j=0;j<ets.size();j++)
					{
						Enemy enemy_tank = ets.get(j);
						if(enemy_tank.isLive)
						{
							boolean isHit = this.hitTank(one_bullet, enemy_tank);
							if(isHit)
							{
								Thread explosion = new Thread(sound_play);
								explosion.start();
								TankGame.addScore();
							}
						}
						else if(enemy_tank.bullets.size()==0)
						{
							ets.remove(enemy_tank);
							//System.out.println("Remain enemy num: "+ets.size());
						}
					}
				}
			}
			
			//ally hit enemy
			for(int i=0;i<ats.size();i++)
			{
				Enemy ally_tank = ats.get(i);
				for(int j=0;j<ally_tank.bullets.size();j++)
				{
					Bullet one_bullet = ally_tank.bullets.get(j);
					//check bullet is valid
					if(one_bullet.isLive)
					{
						//match with every enemy tank
						for(int k=0;k<ets.size();k++)
						{
							Enemy enemy_tank = ets.get(k);
							if(enemy_tank.isLive)
							{
								boolean isHit = this.hitTank(one_bullet, enemy_tank);
								if(isHit)
								{
									Thread explosion = new Thread(sound_play);
									explosion.start();
								}
							}
							else if(enemy_tank.bullets.size()==0)
							{
								ets.remove(enemy_tank);
								//System.out.println("Remain enemy num: "+ets.size());
							}
						}
					}
				}
			}
			
			//enemy hit hero
			for(int i=0;i<ets.size();i++)
			{
				Enemy enemy_tank = ets.get(i);
				for(int j=0;j<enemy_tank.bullets.size();j++)
				{
					Bullet enemy_bullet = enemy_tank.bullets.get(j);
					boolean isHit = this.hitTank(enemy_bullet, hero);
					if(isHit) 
					{
						Thread explosion = new Thread(sound_play);
						explosion.start();
						hero.setX(TankGame.hero_x);
						hero.setY(TankGame.hero_y);
						hero.setDirect(0);
						hero.life--;
						System.out.println("Hero is dead, remain life: "+hero.life);
					}
				}
			}
			//enemy hit ally
			for(int i=0;i<ets.size();i++)
			{
				Enemy enemy_tank = ets.get(i);
				for(int j=0;j<enemy_tank.bullets.size();j++)
				{
					Bullet enemy_bullet = enemy_tank.bullets.get(j);
					for(int k=0;k<ats.size();k++)
					{
						Enemy ally_tank = ats.get(k);
						if(ally_tank.isLive)
						{
							boolean isHit = this.hitTank(enemy_bullet, ally_tank);
							if(isHit)
							{
								Thread explosion = new Thread(sound_play);
								explosion.start();
							}
						}
						else if(ally_tank.bullets.size()==0)
						{	
							ats.remove(ally_tank);
							//System.out.println("Remain ally num: "+ats.size());
						}
					}
				}
			}			
			this.repaint();
		}
	}
}

	

class RecordPanel extends JPanel
{	
	public void paint(Graphics g)
	{
		super.paint(g);
	}
}


