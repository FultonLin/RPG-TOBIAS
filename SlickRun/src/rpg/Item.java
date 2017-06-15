package rpg;

public class Item implements ItemInterface{
	
	private String name;
	private String type;
	private int attack;
	private int attackspd;
	private int def;
	private int num;
	private int heal;

	public Item(String name, String type, int attack, int def, int heal, int num){
		this.name = name;
		this.type = type;
		this.attack = attack;
		this.def = def;
		this.heal = heal;
		this.num = num;
	}
	
	public int getdef(){
		return def;
	}
	
	public String getType(){
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

	@Override
	public int getStackNum() {
		return num;
	}

	@Override
	public void incNum(int num) {
		num += num;
	}

	@Override
	public int getHeal() {
		return heal;
	}
	
}
