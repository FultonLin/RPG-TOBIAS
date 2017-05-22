package rpg;

public class Item implements ItemInterface{
	
	private String name;
	private int attack;
//	private String[] properties;

	public Item(String name,int attack){
		this.name = name;
		this.attack = attack;
//		this.properties = properties;
	}

	@Override
	public String[] getProperties() {
//		return properties;
		return null;
	}
	
	public int getAttack(){
		return attack;
	}

	@Override
	public String getName() {
		return name;
	}
	
	
}
