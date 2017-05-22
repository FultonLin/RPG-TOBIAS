package rpg;

import java.util.ArrayList;

public class Item implements ItemInterface{
	
	private String name;
	private String[] properties;

	public Item(String name,String[] properties){
		this.name = name;
		this.properties = properties;
	}
	
	@Override
	public String getItem() {
		return name;
	}

	@Override
	public String[] getProperties() {
		return properties;
	}

	@Override
	public String getName() {
		return name;
	}
	
	
}
