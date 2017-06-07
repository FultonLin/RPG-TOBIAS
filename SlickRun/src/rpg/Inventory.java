package rpg;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Inventory extends BasicGameState{

	protected static ArrayList<Item> inventory;
	protected static ArrayList<Item> equip;
	private TrueTypeFont font;
	private TextField abc;
	private TextField abcd;
	private TextField abcde;
	
	public Inventory(int inventory) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		inventory = new ArrayList<Item>();
		equip = new ArrayList<Item>();
		Font asd = new Font("Helvetica", Font.BOLD, 36);
		font = new TrueTypeFont(asd , true);
		abc = new TextField(gc, gc.getDefaultFont(), 50, 150, 300, 300);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		abc.render(gc, g);
		g.drawRect(50, 150, 300, 350);
		font.drawString(25, 25, "Inventory", Color.white);
	}
 
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int a) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_I)){
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sbg.enterState(1);
		}
		for(int i = 0; i < inventory.size(); i++){
			abc.setText(inventory.get(i).getName());
		}
	}

	@Override
	public int getID() {
		return 3;
	}

	public ArrayList<Item> getEquip() {
		return equip;  
	}

	public void addEquip(Item equip) {
		for(int i = 0; i < Inventory.equip.size(); i++){
			if(equip.getType() == Inventory.equip.get(i).getType()){
				Inventory.equip.set(i,equip);
			}else{
				Inventory.equip.add(equip);
			}
		}
	}
}
