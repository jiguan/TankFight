import java.util.Vector;

public class Tank
{
	int x, y;
	int direct = 0;
	int speed = 1;
	boolean isLive = false;
	// 0 = up; 1 = right; 2 = down; 3 = left;
	Vector<Bullet> bullets = new Vector<Bullet>();
	Bullet bullet;
	int max_bullet = 5;
	int width = TankGame.width;
	int height = TankGame.height;
	public Tank(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public int getX()
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY()
	{
		return y;
	}
	public void setY(int y)
	{
		this.y = y;
	}
	public int getDirect()
	{
		return direct;
	}
	public void setDirect(int direct)
	{
		this.direct = direct;
	}
	public int getSpeed()
	{
		return speed;
	}
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}
	public void setMaxBullet(int max_bullet)
	{
		this.max_bullet = max_bullet;
	}
	public boolean valid(int x, int y, int direct)
	{
		//System.out.println("The tank position: "+x+" "+y);
		boolean isValid = true;
		switch(direct)
		{
			case 0: 
			{
				if(y - 15 -speed < 0) 
				{
					isValid = false; 
					//System.out.println("The isValid turns");
				}
				
				break;
			}
			case 1: 
			{
				if(x + 15 + speed > width) 
				{
					isValid = false;
				} 
				break;
			}
			case 2: 
			{
				if(y + 15 + speed > height - 50) 
				{
					isValid = false;
				}
				break;
			}
			case 3: 
			{
				if(x - 15 - speed < 0)
				{
					isValid = false;
				}
				break;
			}
		}
		//System.out.println(isValid);
		return isValid;
	}
}

class Hero extends Tank
{
	int life = 2;
	public Hero(int x, int y)
	{
		super(x, y);
		isLive = true;
	}
	public void setLife(int life)
	{
		this.life = life;
	}
	public void fire()
	{
		switch(this.getDirect())
		{
			case 0:
			{
				bullet = new Bullet(this.getX(), this.getY()-15, 0);
				break;
			}
			case 1:
			{
				bullet = new Bullet(this.getX()+15, this.getY(), 1);
				break;
			}
			case 2:
			{
				bullet = new Bullet(this.getX(), this.getY()+15, 2);
				break;
			}
			case 3:
			{
				bullet = new Bullet(this.getX()-15, this.getY(), 3);
				break;
			}
			
		}
		bullets.add(bullet);
		// start bullet thread
		Thread bullet_thread = new Thread(bullet);
		bullet_thread.start();
	}
	
	public void moveUp()
	{
		if(valid(x,y,this.getDirect())) 
		{
			switch (this.getDirect())
			{
				case 0: y = y - speed; break;
				case 1: x = x + speed; break;
				case 2: y = y + speed; break;
				case 3: x = x - speed; break;
			}
		}
	}
	public void turnRight()
	{
		direct = (direct + 1) % 4;
	}
	public void stepBack()
	{
		int back_direct = (direct + 2) % 4;
		if(valid(x,y,back_direct)) 
		{	
			switch (this.getDirect())
			{
				case 0: y = y + speed; break;
				case 1: x = x - speed; break;
				case 2: y = y - speed; break;
				case 3: x = x + speed; break;
			}
		}
	}
	public void turnLeft()
	{
		direct = (direct - 1) % 4;
		if (direct < 0)
			direct = 4 + direct;
	}
	
	
}

class Enemy extends Tank implements Runnable
{
	int step = 30;
	int frozen_time = 50;
	int time = 0;
	boolean isTouch;
	public Enemy(int x, int y) {
		super(x, y);
		isLive = true;
	}
	//get other tanks
	Vector<Enemy> ets = new Vector<Enemy>();
	//get vector of enemy on panel
	public void setEts(Vector<Enemy> ets)
	{
		this.ets = ets;
	}
	//check whether touch other tanks
	public boolean touchOthers()
	{
		isTouch = false;
		switch(this.direct) {
		case 0:
		case 2:
			//get out all enemy tanks
			for(int i=0;i<ets.size();i++) 
			{
				Enemy enemy_tank = ets.get(i);
				//if not yourself
				if(enemy_tank!=this&&enemy_tank.isLive)
				{
					switch(enemy_tank.direct) {
					case 0:
					case 2:
						if((this.x+10>=enemy_tank.x-10)&&(this.x-10<enemy_tank.x+10)&&(this.y-15<enemy_tank.y+15)&&(this.y+15>enemy_tank.y-15))
							isTouch = true;
						break;
					case 1:
					case 3:
						if((this.x+10>=enemy_tank.x-15)&&(this.x-10<enemy_tank.x+15)&&(this.y-15<enemy_tank.y+10)&&(this.y+15>enemy_tank.y-10))
							isTouch = true;
						break;
					}
				}
			}
			break;
		case 1:
		case 3:
			for(int i=0;i<ets.size();i++) 
			{
				Enemy enemy_tank = ets.get(i);
				//if not yourself
				if(enemy_tank!=this&&enemy_tank.isLive)
				{
					switch(enemy_tank.direct) {
					case 0:
					case 2:
						if((this.y+10>=enemy_tank.y-15)&&(this.y-10<enemy_tank.y+15)&&(this.x+15>enemy_tank.x-10)&&(this.x-15<=enemy_tank.x+10))
							isTouch = true;
						break;
					case 1:
					case 3:
						if((this.y+10>=enemy_tank.y-10)&&(this.y-10<enemy_tank.y+10)&&(this.x+15>enemy_tank.x-15)&&(this.x-15<=enemy_tank.x+15))
							isTouch = true;
						break;
					}
				}
			}
			break;
		}
		return isTouch;
	}
	
	public void run() {
		while (isLive) {
			//random direction
			this.direct = (int)(Math.random()*4);
			switch(this.direct) {
				case 0: {
					for (int i=0;i<step;i++) {
						if (valid(x, y, 0)&&(!this.touchOthers()))
							y -= speed;
						else break;
						try {
							Thread.sleep(frozen_time);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
				}
				case 1: {
					for (int i=0;i<step;i++) {
						if (valid(x, y, 1)&&(!this.touchOthers()))
							x += speed;
						else break;
						try {
							Thread.sleep(frozen_time);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
				}
				case 2: {
					for (int i=0;i<step;i++) {
						if (valid(x, y, 2)&&(!this.touchOthers()))
							y += speed;
						else break;
						try {
							Thread.sleep(frozen_time);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
				}
				case 3: {
					for (int i=0;i<step;i++) {
						if (valid(x, y, 3)&&(!this.touchOthers()))
							x -= speed;
						else break;
						try {
							Thread.sleep(frozen_time);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
				}
			}	
			this.time++;
			if (time%1==0)
			{	
				if(this.isLive) 
				{
					if(bullets.size()<5)
					{
						Bullet bullet = null;
						switch(this.direct)
						{
							case 0:									
								bullet = new Bullet(this.x, this.y-15,0);
								break;
							case 1:
								bullet = new Bullet(this.x+15, this.y,1);
								break;
							case 2:
								bullet = new Bullet(this.x, this.y+15,2);
								break;
							case 3:
								bullet = new Bullet(this.x-15, this.y,3);
								break;
						}
						this.bullets.add(bullet);
						Thread bullet_thread = new Thread(bullet);
						bullet_thread.start();
					}
				}
			}
		}
	}
	public void stop() {
			Thread.currentThread().interrupt();
	}
}