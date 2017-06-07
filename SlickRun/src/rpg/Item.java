package rpg;

public class Item implements ItemInterface{
	
	private String name;
	private int type;
	private int attack;
	private int attackspd;
	private int def;
//	private String[] properties;

	public Item(String name, int type, int attack, int attackspd, int def){
		this.name = name;
		this.type = type;
		this.attack = attack;
		this.attackspd = attackspd;
		this.def = def;
	}
	
	public int getdef(){
		return def;
	}
	
	public int getType(){
		return type;  
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
