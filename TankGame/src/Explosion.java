
public class Explosion {
	int x, y;
	int life = 9;
	boolean isLive = true;
	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public void lapse() {
		if(life>0) life--;
		else isLive = false;
	}
	
}
