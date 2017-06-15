package rpg;

public class Monster implements MonsterInterface{
	
//	private Player target;
	private String name;
	private int hp;
	private int dmg; 	
	
	public Monster (String name, int hp, int dmg){
		this.name = name;
		this.hp = hp;
		this.dmg = dmg;
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
	
	public String getName(){
		return name;
	}
}
