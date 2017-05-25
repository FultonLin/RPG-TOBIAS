package rpg;

public class Monster implements MonsterInterface{
	
	private String name;
	private int hp;
	private int dmg;
	private int spd;
	private int xpos;
	private int ypos;
	private int width;
	private int height;
	
	
	public Monster (String name, int hp, int dmg, int spd, int xpos, int ypos, int height, int width){
		this.name = name;
		this.hp = hp;
		this.dmg = dmg;
		this.spd = spd;
		this.xpos = xpos;
		this.ypos = ypos;
		this.height = height;
		this.width = width;
	}

	@Override
	public int gethp() {
		return hp;
	}

	@Override
	public int getdmg() {
		return dmg;
	}

	@Override
	public void decreaseHp(int dmg) {
		hp -= dmg;
	}

	@Override
	public int getSpd() {
		return spd;
	}
	
	public int getxpos(){
		return xpos;
	}
	
	public int getypos(){
		return ypos;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
}
