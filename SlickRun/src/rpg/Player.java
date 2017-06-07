package rpg;

public class Player implements PlayerInterface,Runnable{
	
	private double yuushaX = 0;
	private double yuushaY = 0;
	private int dmg = 10;
	private int hp = 100;
	private int atkSpd = 500;

	
	public Player(){
	}
	
	@Override
	public double getYuushaX() {
		return yuushaX;  
	}

	@Override
	public double getYuushaY() {
		return yuushaY;
	}

	@Override
	public int getDmg() {
		return dmg;
	}
 
	@Override
	public int getHp() {
		return hp;
	}

	@Override
	public int getAtkspd() {
		return atkSpd;
	}

	@Override
	public void run() {
		
	}

	@Override
	public void setYuushaX(double yuushaX) {
		this.yuushaX = yuushaX;
	}

	@Override
	public void setYuushaY(double yuushaY) {
		this.yuushaY = yuushaY;
	}
	
	@Override
	public void decHp(int dmg) {
		hp -= dmg;
	}
}
