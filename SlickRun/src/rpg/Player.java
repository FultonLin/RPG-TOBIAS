package rpg;

public class Player implements PlayerInterface,Runnable{
	private float yuushaX = 0;
	private float yuushaY = 0;
	private int dmg = 10;
	private int hp = 100;
	private int atkSpd = 500;

	
	public Player(){
	}
	
	@Override
	public float getYuushaX() {
		return yuushaX;
	}

	@Override
	public float getYuushaY() {
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

}
