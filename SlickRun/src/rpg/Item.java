package rpg;

public class Item implements ItemInterface{
	
	private String name;
	private int attack;
	private int attackspd;
//	private String[] properties;

	public Item(String name, int attack, int attackspd){
		this.name = name;
		this.attack = attack;
		this.attackspd = attackspd;
	}
	
	public int getAttack(){
		return attack;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getAttackSpd() {
		return attackspd;
	}
	
}
