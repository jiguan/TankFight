
class Bullet implements Runnable {

	int x, y, direct;
	int speed = 3;
	boolean isLive = true;
	public Bullet(int x, int y, int direct)
	{
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	public void run()
	{
		while(isLive)
		{
			switch(direct)
			{
				case 0: y = y - speed; break;
				case 1: x = x + speed; break;
				case 2: y = y + speed; break;
				case 3: x = x - speed; break;
			}
			//System.out.println("Bullet coordinate: "+x+" "+y);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//check bullet is live?
			if(x<0 || x>TankGame.width || y<0 || y>TankGame.height)
			{
				isLive = false;
				break;
			}
		}
	}
}
